package cloud;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/8/31 15:09
 */
public class CimGToSvg {
    private String cimgPath = "D:/svg/";
    private final static String G_FILEPATH = "D:/svg/display/fac/";
    private final static String FORMAT_G_FILEPATH = "D:/svg/display/format_G/";
    private boolean image = true;
    private Map<String, String> centerMap = new HashMap<String, String>();
    private Map<String, String> center0Map = new HashMap<String, String>();
    private Map<String, String> colorMap = new HashMap<String, String>();

    /**
    * TODO 解析D:/svg/display/fac 下所有g文件 在 D:/svg/display/format_G下生成svg文件
    * @return void
    * @author xuhong.ding
    * @since 2020/9/2 9:09
    */
    public void parseGFiles() throws Exception{
        File files = new File(G_FILEPATH);
        if(files.isDirectory()){
            File[] listFiles = files.listFiles();
            for (File file:listFiles ) {
                getSvg(file.getName());
            }
        }
    }

    /**
    * TODO 解析g文件为svg文件
    * g文件要放在cimgPath = "D:/svg/display/fac" 下  图源信息element放在同"D:/svg/"下
    * @param type fac
    * @param name 文件名字
    * @return java.lang.String
    * @author xuhong.ding
    * @since 2020/9/2 8:19
    */
    public String getSvg(String name) {
        String result = null;
        try {
            Document cimg = new SAXReader().read(new BufferedInputStream(new FileInputStream(G_FILEPATH + name)));
            Document svg = DocumentHelper.createDocument();
            Element cimgRoot = cimg.getRootElement();
            int width = Double.valueOf(cimgRoot.attributeValue("w")).intValue();
            int height = Double.valueOf(cimgRoot.attributeValue("h")).intValue();
            Element svgRoot = svg.addElement("svg", "http://www.w3.org/2000/svg");
            svgRoot.addAttribute("version", "1.1");
            svgRoot.addNamespace("xlink", "http://www.w3.org/1999/xlink");
            svgRoot.addAttribute("viewBox", "0,0," + width + "," + height);
            svgRoot.addAttribute("width", "100%");
            svgRoot.addAttribute("height", "100%");
            Attribute bgc = cimgRoot.attribute("bgc");
            if (bgc != null) {
                Attribute style = svgRoot.attribute("style");
                if (style != null) {
                    style.setValue("background-color:rgb(" + bgc.getValue() + ");" + style.getValue());
                } else {
                    svgRoot.addAttribute("style", "background-color:rgb(" + bgc.getValue() + ");");
                }
            }
            List<Element> eles = (List<Element>) cimg.selectNodes("G/Layer/*").stream().filter(node->node instanceof Element).map(node->(Element)node).collect(Collectors.toList());
            Set<String> symbols = new HashSet<String>();
            for (Element ele : eles) {
                Attribute devref = ele.attribute("devref");
                if (devref != null) {
                    symbols.add(ele.getName() + "=" + devref.getValue());
                }
                Attribute id = ele.attribute("id");
                Attribute voltype = ele.attribute("voltype");
                if (id != null && voltype != null) {
                    colorMap.put(id.getValue(), colorConvert(voltype.getValue()));
                }
            }
            Element defs = svgRoot.addElement("defs");
            defs.addAttribute("id", "defs");
            for (String symbol : symbols) {
                String temp[] = symbol.split("=");
                defs = getSymbol(temp[0], temp[1].replace("#", ""), defs);
            }
            while (eles.size() > 0) {
                for (int i = 0; i < eles.size(); i++) {
                    Element ele = eles.get(i);
                    Element obj = svgRoot.addElement("g");
                    obj.addAttribute("id", ele.attributeValue("id"));
                    obj.addAttribute("gname", ele.getName());
                    if (ele.attribute("keyid") != null) {
                        obj.addAttribute("keyid", ele.attributeValue("keyid"));
                    }
                    if (ele.attribute("keyid1") != null) {
                        obj.addAttribute("keyid1", ele.attributeValue("keyid1"));
                    }
                    if (ele.attribute("keyid2") != null) {
                        obj.addAttribute("keyid2", ele.attributeValue("keyid2"));
                    }
                    if (ele.attribute("p_FatherObjId") != null) {
                        obj.addAttribute("fid", ele.attributeValue("p_FatherObjId"));
                    }
                    Element temp = parseCimG(ele, obj, true);
                    if (temp != null) {
                        obj = temp;
                        eles.remove(i--);
                    } else {
                        svgRoot.remove(obj);
                    }
                }
            }
            result = svgRoot.asXML();

			svg.addDocType("svg", "-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd", null);
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 利用格式化类对编码进行设置
			format.setEncoding("GBK");
			FileOutputStream output = new FileOutputStream(
					new File(FORMAT_G_FILEPATH+name+".svg"));
			XMLWriter writer = new XMLWriter(output, format);
			writer.write(svg);
			writer.flush();
			writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private Element getSymbol(String name, String href, Element defs) throws Exception {
        try {
            String[] hrefs = href.split(":");
            if (hrefs.length != 2)
                return null;
            String SymbolPath = cimgPath;
            if (name.equals("Curve")) {
                String[] hrefsdir = hrefs[0].split("\\.");
                String dir = hrefsdir[hrefsdir.length - 3];
                SymbolPath += name.split("_")[0].toLowerCase() + "/" + dir + "/" + hrefs[0];
            } else {
                SymbolPath += "/element/" + name.split("_")[0].toLowerCase() + "/" + hrefs[0];
            }
            File SymbolFile = new File(SymbolPath);
            if (!SymbolFile.exists()) {
                System.out.println(SymbolPath);
                return defs;
            }
            Document cimg = new SAXReader().read(SymbolFile);
            Element cimgRoot = cimg.getRootElement().element(name);
            String center = cimgRoot.attributeValue("AlignCenter");
            String id = cimgRoot.attributeValue("id");
            centerMap.put(id, center);

            if (cimgRoot.attributeValue("w") != null && cimgRoot.attributeValue("h") != null) {
                String center0 = Double.valueOf(cimgRoot.attributeValue("w")) / 2 + ","
                        + Double.valueOf(cimgRoot.attributeValue("h")) / 2;
                center0Map.put(id, center0);
            }
            List<Element> layers = (List<Element>) cimgRoot.selectNodes("Layer").stream().filter(node->node instanceof Element).map(node->(Element)node).collect(Collectors.toList());;
            for (int i = 0; i < layers.size(); i++) {
                List<Element> eles = layers.get(i).elements();
                if (eles.isEmpty()) {
                    continue;
                }
                Element symbol0 = defs.addElement("symbol");
                String symbolId0 = id + "_" + i + "_0";
                symbol0.addAttribute("id", symbolId0);
                Element symbol1 = null;
                for (Element ele : eles) {
                    if (ele.attributeValue("sta") != null) {
                        if ("0".equals(ele.attributeValue("sta"))) {
                            symbol0 = parseCimG(ele, symbol0, "Sensitive".equals(name));
                        } else if ("1".equals(ele.attributeValue("sta"))) {
                            if (symbol1 == null) {
                                symbol1 = defs.addElement("symbol");
                                String symbolId1 = id + "_" + i + "_1";
                                symbol1.addAttribute("id", symbolId1);
                            }
                            symbol1 = parseCimG(ele, symbol1, "Sensitive".equals(name));
                        }
                    } else {
                        symbol0 = parseCimG(ele, symbol0, "Sensitive".equals(name));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defs;
    }

    private Element parseCimG(Element ele, Element parent, boolean isObj) {
        if ("line".equals(ele.getName())) {
            return parseLine(ele, parent, isObj);
        } else if ("rect".equals(ele.getName())) {
            return parseRect(ele, parent, isObj);
        } else if ("circle".equals(ele.getName())) {
            return parseCircle(ele, parent, isObj);
        } else if ("ellipse".equals(ele.getName())) {
            return parseEllipse(ele, parent, isObj);
        } else if ("ellipsearc".equals(ele.getName()) || "circlearc".equals(ele.getName())) {
            return parseArc(ele, parent, isObj);
        } else if ("pin".equals(ele.getName())) {
            return parseCircle(ele, parent, isObj);
        } else if ("triangle".equals(ele.getName())) {
            return parseTriangle(ele, parent, isObj);
        } else if ("polyline".equals(ele.getName())) {
            return parsePolyline(ele, parent, isObj);
        } else if ("polygon".equals(ele.getName())) {
            return parsePolygon(ele, parent, isObj);
        } else if ("diamond".equals(ele.getName())) {
            return parent;
        } else if ("text".equals(ele.getName())) {
            return parseString(ele, parent, isObj);
        } else if ("DText".equals(ele.getName()) || "Text".equals(ele.getName())) {
            return parseString(ele, parent, isObj);
        } else if ("Arrester".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Ascoil".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Capacitor_P".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("CBreaker".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Disconnector".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("DollyBreaker".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Fuse".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Generator".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("GroundDisconnector".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Gzp".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Protect".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("PT".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("RcBreaker".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Reactor_P".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("RectifierInverter".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Sensitive".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Zxddd".equals(ele.getName())) {
            return parseOneNode(ele, parent);
        } else if ("Transformer2".equals(ele.getName())) {
            return parseTwoNode(ele, parent);
        } else if ("Transformer3".equals(ele.getName())) {
            return parseThreeNode(ele, parent);
        } else if ("ACLineEnd".equals(ele.getName())) {
            return parseACLine(ele, parent);
        } else if ("ACLine".equals(ele.getName())) {
            return parseACLine(ele, parent);
        } else if ("ConnectLine".equals(ele.getName())) {
            return parseConnectLine(ele, parent);
        } else if ("Bus".equals(ele.getName())) {
            return parseBus(ele, parent);
        } else if ("image".equals(ele.getName()) && image) {
            return parseImage(ele, parent);
        } else {
            parent.add(ele.detach());
        }
        return parent;
    }

    private Element parseImage(Element ele, Element parent) {
        int startx = Double.valueOf(ele.attributeValue("x")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y")).intValue();
        int width = Double.valueOf(ele.attributeValue("w")).intValue();
        int height = Double.valueOf(ele.attributeValue("h")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String href = ele.attributeValue("ahref");
        String transform = ele.attributeValue("tfr");
        Element image = parent.addElement("image");
        image.addAttribute("x", Math.abs(startx) + "");
        image.addAttribute("y", Math.abs(starty) + "");
        image.addAttribute("width", Math.abs(width) + "");
        image.addAttribute("height", Math.abs(height) + "");
        image.addAttribute("stroke", linecolor);
        //image.addAttribute("xlink:href", "cimg/image/" + href);
		image.addAttribute("xlink:href", "http://gw.alicdn.com/tps/i4/TB1QygcLFXXXXcmaXXXTQYPHFXX-60-41.png");
        image.addAttribute("transform", transform);
        return parent;

    }

    private Element parseBus(Element ele, Element parent) {
        int startx = Double.valueOf(ele.attributeValue("x1")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y1")).intValue();
        int endx = Double.valueOf(ele.attributeValue("x2")).intValue();
        int endy = Double.valueOf(ele.attributeValue("y2")).intValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        int linestyle = Double.valueOf(ele.attributeValue("ls")).intValue();
        String voltype = ele.attributeValue("voltype");
        String color = colorConvert(voltype);
        Element line = parent.addElement("line");
        parent.addAttribute("voltype", voltype);
        line.addAttribute("x1", startx + "");
        line.addAttribute("y1", starty + "");
        line.addAttribute("x2", endx + "");
        line.addAttribute("y2", endy + "");
        line.addAttribute("stroke-width", linewidth + "");

        switch (linestyle) {
            case 2:
                line.addAttribute("stroke-dasharray", "8 2 8 2 8 2");
                break;
            case 3:
                line.addAttribute("stroke-dasharray", "0.25 0.25 0.25 0.25 0.25 0.25");
                break;
            case 4:
                line.addAttribute("stroke-dasharray", "0.75 0.25 0.75 0.25 0.75 0.25");
                break;

        }
        line.addAttribute("stroke", color);
        line.addAttribute("fill", "none");
        colorMap.put(ele.attributeValue("id"), color);
        return parent;
    }

    private Element parseConnectLine(Element ele, Element parent) {
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        Element path = parent.addElement("path");
        path.addAttribute("d", "M" + ele.attributeValue("d").replaceAll(" ", " L"));
        path.addAttribute("stroke-width", linewidth + "");
        String color = "rgb(" + ele.attributeValue("lc") + ")";
        if (ele.attributeValue("link") != null) {
            path.addAttribute("link", ele.attributeValue("link"));
            color = null;
            String[] links = ele.attributeValue("link").split(";");
            for (String link : links) {
                color = colorMap.get(link.split(",")[2]);
                if (color != null) {
                    path.addAttribute("stroke", color);
                    colorMap.put(ele.attributeValue("id"), color);
                    break;
                }
            }
        }
        if (color == null) {
            return null;
        }
        path.addAttribute("fill", "none");
        return parent;
    }

    private Element parseACLine(Element ele, Element parent) {
        String color = "rgb(" + ele.attributeValue("lc") + ")";
        String voltype = ele.attributeValue("voltype");
        if (voltype != null) {
            color = colorConvert(voltype);
        } else {
            String voltype1 = ele.attributeValue("voltype1");
            color = colorConvert(voltype1);
        }
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        Element polyline = parent.addElement("polyline");
        polyline.addAttribute("points", ele.attributeValue("d"));
        polyline.addAttribute("stroke-width", linewidth + "");
        polyline.addAttribute("stroke", color);
        polyline.addAttribute("fill", "none");
        colorMap.put(ele.attributeValue("id"), color);
        return parent;
    }

    private Element parseOneNode(Element ele, Element parent) {
        String color = "";
        String voltype = ele.attributeValue("voltype");
        if (voltype == null) {
            voltype = ele.attributeValue("voltype1");
            if (voltype != null) {
                color = colorConvert(voltype);
            }
        } else {
            color = colorConvert(voltype);
        }
        double startx = Double.valueOf(ele.attributeValue("x")).doubleValue();
        double starty = Double.valueOf(ele.attributeValue("y")).doubleValue();
        String href = ele.attributeValue("devref").split(":")[1];
        String transform = ele.attribute("tfr") != null
                ? "translate(" + startx + "," + starty + ") " + ele.attributeValue("tfr")
                : "translate(" + startx + "," + starty + ")";
        Element use = parent.addElement("use");
        use.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        if (!"".equals(color)) {
            use.addAttribute("stroke", color);
            use.addAttribute("fill", color);
        }
        use.addAttribute("xlink:href", "#" + href + "_0_0");
        colorMap.put(ele.attributeValue("id"), color);
        return parent;
    }

    private Element parseTwoNode(Element ele, Element parent) {
        String voltype1 = ele.attributeValue("voltype1");
        String color1 = colorConvert(voltype1);
        String voltype2 = ele.attributeValue("voltype2");
        String color2 = colorConvert(voltype2);
        double startx = Double.valueOf(ele.attributeValue("x")).doubleValue();
        double starty = Double.valueOf(ele.attributeValue("y")).doubleValue();
        String href = ele.attributeValue("devref").split(":")[1];
        String transform = ele.attribute("tfr") != null
                ? "translate(" + startx + "," + starty + ") " + ele.attributeValue("tfr")
                : "translate(" + startx + "," + starty + ")";
        Element use1 = parent.addElement("use");
        use1.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        use1.addAttribute("stroke", color1);
        use1.addAttribute("fill", color1);
        use1.addAttribute("xlink:href", "#" + href + "_0_0");
        Element use2 = parent.addElement("use");
        use2.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        use2.addAttribute("stroke", color2);
        use2.addAttribute("fill", color2);
        use2.addAttribute("xlink:href", "#" + href + "_1_0");
        return parent;
    }

    private Element parseThreeNode(Element ele, Element parent) {
        String voltype1 = ele.attributeValue("voltype1");
        String color1 = colorConvert(voltype1);
        String voltype2 = ele.attributeValue("voltype2");
        String color2 = colorConvert(voltype2);
        String voltype3 = ele.attributeValue("voltype3");
        String color3 = colorConvert(voltype3);
        double startx = Double.valueOf(ele.attributeValue("x")).doubleValue();
        double starty = Double.valueOf(ele.attributeValue("y")).doubleValue();
        String href = ele.attributeValue("devref").split(":")[1];
        String transform = ele.attribute("tfr") != null
                ? "translate(" + startx + "," + starty + ") " + ele.attributeValue("tfr")
                : "translate(" + startx + "," + starty + ")";
        Element use1 = parent.addElement("use");
        use1.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        use1.addAttribute("stroke", color1);
        use1.addAttribute("fill", color1);
        use1.addAttribute("xlink:href", "#" + href + "_0_0");
        Element use2 = parent.addElement("use");
        use2.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        use2.addAttribute("stroke", color2);
        use2.addAttribute("fill", color2);
        use2.addAttribute("xlink:href", "#" + href + "_1_0");
        Element use3 = parent.addElement("use");
        use3.addAttribute("transform", transByCenter(transform, centerMap.get(href), center0Map.get(href)));
        use3.addAttribute("stroke", color3);
        use3.addAttribute("fill", color3);
        use3.addAttribute("xlink:href", "#" + href + "_2_0");
        return parent;
    }

    private Element parseLine(Element ele, Element parent, boolean isObj) {
        int startx = Double.valueOf(ele.attributeValue("x1")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y1")).intValue();
        int endx = Double.valueOf(ele.attributeValue("x2")).intValue();
        int endy = Double.valueOf(ele.attributeValue("y2")).intValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        int linestyle = Double.valueOf(ele.attributeValue("ls")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        Element line = parent.addElement("line");
        line.addAttribute("x1", startx + "");
        line.addAttribute("y1", starty + "");
        line.addAttribute("x2", endx + "");
        line.addAttribute("y2", endy + "");
        line.addAttribute("stroke-width", linewidth + "");
        line.addAttribute("fill-opacity", "0");

        switch (linestyle) {
            case 2:
                line.addAttribute("stroke-dasharray", "8 2 8 2 8 2");
                break;
            case 3:
                line.addAttribute("stroke-dasharray", "0.25 0.25 0.25 0.25 0.25 0.25");
                break;
            case 4:
                line.addAttribute("stroke-dasharray", "0.75 0.25 0.75 0.25 0.75 0.25");
                break;

        }
        if (isObj) {
            line.addAttribute("stroke", linecolor);
            line.addAttribute("fill", "none");
        }
        return parent;
    }

    private Element parseRect(Element ele, Element parent, boolean isObj) {
        int startx = Double.valueOf(ele.attributeValue("x")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y")).intValue();
        int width = Double.valueOf(ele.attributeValue("w")).intValue();
        int height = Double.valueOf(ele.attributeValue("h")).intValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        int linestyle = Double.valueOf(ele.attributeValue("ls")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String fillcolor = "rgb(" + ele.attributeValue("lc") + ")";
        int fillmode = Double.valueOf(ele.attributeValue("fm")).intValue();
        int center_x = startx + width / 2;
        int center_y = starty + height / 2;
        if (width < 0)
            startx += width;
        if (height < 0)
            starty += height;
        if (linewidth <= 0)
            linewidth = 1;
        if (fillmode == 0) {
            fillcolor = "none";
        }
        Element rect = parent.addElement("rect");
        rect.addAttribute("x", Math.abs(startx) + "");
        rect.addAttribute("y", Math.abs(starty) + "");
        rect.addAttribute("width", Math.abs(width) + "");
        rect.addAttribute("height", Math.abs(height) + "");
        rect.addAttribute("stroke-width", linewidth + "");
        if (ele.attributeValue("sta") != null && "1".equals(ele.attributeValue("sta"))) {
            rect.addAttribute("fill-opacity", "1");
        } else {
            rect.addAttribute("fill-opacity", "0");
        }
        Attribute transform = ele.attribute("tfr");
        int angle = 0;
        if (transform != null) {
            String rotate = getTransform(transform.getValue(), "rotate");
            angle = Integer.valueOf(rotate.split(",")[0]);
            if (angle >= 360) {
                angle -= 360;
            }
        }
        rect.addAttribute("transform", "rotate(" + angle + "," + center_x + "," + center_y + ")");
        switch (linestyle) {
            case 3:
                rect.addAttribute("stroke-dasharray", "2.5 2.5");
                break;

        }
        if (isObj) {
            rect.addAttribute("stroke", linecolor);
            rect.addAttribute("fill", fillcolor);
        }
        return parent;
    }

    private Element parseCircle(Element ele, Element parent, boolean isObj) {
        double cx = Double.valueOf(ele.attributeValue("cx")).doubleValue();
        double cy = Double.valueOf(ele.attributeValue("cy")).doubleValue();
        double r = Double.valueOf(ele.attributeValue("r")).doubleValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String fillcolor = "rgb(" + ele.attributeValue("lc") + ")";
        int fillmode = Double.valueOf(ele.attributeValue("fm")).intValue();
        if (fillmode == 0) {
            fillcolor = "none";
        }
        Element circle = parent.addElement("circle");
        circle.addAttribute("cx", cx + "");
        circle.addAttribute("cy", cy + "");
        circle.addAttribute("r", Math.abs(r) + "");
        circle.addAttribute("stroke-width", linewidth + "");
        circle.addAttribute("fill-opacity", "0");
        if (isObj) {
            circle.addAttribute("stroke", linecolor);
            circle.addAttribute("fill", fillcolor);
        }
        return parent;
    }

    private Element parseEllipse(Element ele, Element parent, boolean isObj) {
        double cx = Double.valueOf(ele.attributeValue("cx")).doubleValue();
        double cy = Double.valueOf(ele.attributeValue("cy")).doubleValue();
        double rx = Double.valueOf(ele.attributeValue("rx")).doubleValue();
        double ry = Double.valueOf(ele.attributeValue("ry")).doubleValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String fillcolor = "rgb(" + ele.attributeValue("lc") + ")";
        int fillmode = Double.valueOf(ele.attributeValue("fm")).intValue();
        if (fillmode == 0) {
            fillcolor = "none";
        }
        Element ellipse = parent.addElement("ellipse");
        ellipse.addAttribute("cx", cx + "");
        ellipse.addAttribute("cy", cy + "");
        ellipse.addAttribute("rx", Math.abs(rx) + "");
        ellipse.addAttribute("ry", Math.abs(ry) + "");
        ellipse.addAttribute("stroke-width", linewidth + "");
        ellipse.addAttribute("fill-opacity", "0");
        if (isObj) {
            ellipse.addAttribute("stroke", linecolor);
            ellipse.addAttribute("fill", fillcolor);
        }
        return parent;
    }

    private Element parseArc(Element ele, Element parent, boolean isObj) {
        try {
            double centerx = Double.valueOf(ele.attributeValue("cx")).intValue();
            double centery = Double.valueOf(ele.attributeValue("cy")).intValue();
            String temp = ele.attributeValue("rx") == null ? ele.attributeValue("r") : ele.attributeValue("rx");
            double rx = 0;
            if (temp == null) {
                //
            } else {
                rx = Double.valueOf(temp).intValue();
            }
            double ry = 0;
            temp = ele.attributeValue("ry") == null ? ele.attributeValue("r") : ele.attributeValue("ry");
            if (temp == null) {
                //
            } else {
                ry = Double.valueOf(temp).intValue();
            }
            int anglestart = Double.valueOf(ele.attributeValue("a1")).intValue();
            int angleend = Double.valueOf(ele.attributeValue("a2")).intValue();
            int rotateangle = Integer.parseInt(getTransform(ele.attributeValue("tfr"), "rotate"));
            int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
            String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
            double startx, starty, endx, endy;
            int angle = angleend - anglestart;
            if (angle < 0)
                angle += 360;

            switch (anglestart) {
                case 0:
                    startx = centerx + rx;
                    starty = centery;
                    break;
                case 90:
                    startx = centerx;
                    starty = centery - ry;
                    break;
                case 180:
                    startx = centerx - rx;
                    starty = centery;
                    break;
                case 270:
                    startx = centerx;
                    starty = centery + ry;
                    break;
                default:
                    double x = (rx * ry)
                            / Math.sqrt(Math.pow(ry, 2) + Math.pow(rx * Math.tan(Math.toRadians(anglestart)), 2));
                    double y = x * Math.tan(Math.toRadians(anglestart));
                    if (anglestart > 90 && anglestart < 270) {
                        startx = centerx - x;
                        starty = centery + y;
                    } else {
                        startx = centerx + x;
                        starty = centery - y;
                    }
            }
            switch (angleend) {
                case 0:
                    endx = centerx + rx - startx;
                    endy = centery - starty;
                    break;
                case 90:
                    endx = centerx - startx;
                    endy = centery - ry - starty;
                    break;
                case 180:
                    endx = centerx - rx - startx;
                    endy = centery - starty;
                    break;
                case 270:
                    endx = centerx - startx;
                    endy = centery + ry - starty;
                    break;
                default:
                    double x = (rx * ry)
                            / Math.sqrt(Math.pow(ry, 2) + Math.pow(rx * Math.tan(Math.toRadians(angleend)), 2));
                    double y = x * Math.tan(Math.toRadians(angleend));
                    if (angleend > 90 && angleend < 270) {
                        endx = centerx - x - startx;
                        endy = centery + y - starty;
                    } else {
                        endx = centerx + x - startx;
                        endy = centery - y - starty;
                    }
            }
            Element path = parent.addElement("path");
            path.addAttribute("d",
                    "M" + Math.round(startx) + "," + Math.round(starty) + " a" + Math.round(rx) + " " + Math.round(ry)
                            + " " + rotateangle + " " + (Math.abs(angle) < 180 ? 0 : 1) + " 0 " + Math.round(endx) + ","
                            + Math.round(endy));
            path.addAttribute("stroke-width", linewidth + "");
            path.addAttribute("fill-opacity", "0");
            path.addAttribute("transform", "rotate(" + rotateangle + "," + centerx + "," + centery + ")");
            if (isObj) {
                path.addAttribute("stroke", linecolor);
                path.addAttribute("fill", "none");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parent;
    }

    private Element parseTriangle(Element ele, Element parent, boolean isObj) {
        int startx = Double.valueOf(ele.attributeValue("x")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y")).intValue();
        int width = Double.valueOf(ele.attributeValue("w")).intValue();
        int height = Double.valueOf(ele.attributeValue("h")).intValue();
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        int linestyle = Double.valueOf(ele.attributeValue("ls")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String fillcolor = "rgb(" + ele.attributeValue("lc") + ")";
        int fillmode = Double.valueOf(ele.attributeValue("fm")).intValue();
        if (width < 0)
            startx += width;
        if (height < 0)
            starty += height;
        if (linewidth <= 0)
            linewidth = 1;
        if (fillmode == 0) {
            fillcolor = "none";
        }
        Attribute transform = ele.attribute("tfr");
        int angle = 0;
        if (transform != null) {
            String rotate = getTransform(transform.getValue(), "rotate");
            angle = Integer.valueOf(rotate.split(",")[0]);
            if (angle >= 360) {
                angle -= 360;
            }
        }
        Element polygon = parent.addElement("polygon");
        switch (angle) {
            case 0:
                polygon.addAttribute("points",
                        startx + "," + (starty + Math.abs(height)) + " " + (startx + Math.abs(width) / 2) + "," + starty
                                + " " + (startx + Math.abs(width)) + "," + (starty + Math.abs(height)));
                break;
            case 90:
                polygon.addAttribute("points", startx + "," + starty + " " + (startx + Math.abs(width)) + ","
                        + (starty + Math.abs(height) / 2) + " " + startx + "," + (starty + Math.abs(height)));
                break;
            case 180:
                polygon.addAttribute("points", startx + "," + starty + " " + (startx + Math.abs(width) / 2) + ","
                        + (starty + Math.abs(height)) + " " + (startx + Math.abs(width)) + "," + starty);
                break;
            case 270:
                polygon.addAttribute("points",
                        startx + "," + (starty + Math.abs(height) / 2) + " " + (startx + Math.abs(width)) + "," + starty
                                + " " + (startx + Math.abs(width)) + "," + (starty + Math.abs(height)));
                break;
            default:
                polygon.addAttribute("points",
                        startx + "," + (starty + Math.abs(height)) + " " + (startx + Math.abs(width) / 2) + "," + starty
                                + " " + (startx + Math.abs(width)) + "," + (starty + Math.abs(height)));
                polygon.addAttribute("transform", "rotate(" + angle + "," + (startx + Math.abs(width) / 2) + ","
                        + (starty + Math.abs(height) / 2) + ")");
                break;
        }
        polygon.addAttribute("stroke-width", linewidth + "");
        polygon.addAttribute("fill-opacity", "0");
        if (isObj) {
            polygon.addAttribute("stroke", linecolor);
            polygon.addAttribute("fill", "none");
        }
        return parent;
    }

    private Element parsePolyline(Element ele, Element parent, boolean isObj) {
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        Element polyline = parent.addElement("polyline");
        polyline.addAttribute("points", ele.attributeValue("d"));
        polyline.addAttribute("stroke-width", linewidth + "");
        polyline.addAttribute("fill", "none");
        if (isObj) {
            polyline.addAttribute("stroke", linecolor);
            polyline.addAttribute("fill", "none");
        }
        return parent;
    }

    private Element parsePolygon(Element ele, Element parent, boolean isObj) {
        int linewidth = Double.valueOf(ele.attributeValue("lw")).intValue();
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        Element polygon = parent.addElement("polygon");
        polygon.addAttribute("points", ele.attributeValue("d"));
        polygon.addAttribute("stroke-width", linewidth + "");
        polygon.addAttribute("fill", "none");
        if (isObj) {
            polygon.addAttribute("stroke", linecolor);
            polygon.addAttribute("fill", "none");
        }
        return parent;
    }

    private Element parseString(Element ele, Element parent, boolean isObj) {
        int startx = Double.valueOf(ele.attributeValue("x")).intValue();
        int starty = Double.valueOf(ele.attributeValue("y")).intValue();
        int fontsize = Double.valueOf(ele.attributeValue("fs")).intValue();
        String s_FontWidth = ele.attributeValue("p_FontWidth");
        String s_FontHeight = ele.attributeValue("p_FontHeight");
        int fontwidth = 0, fontheight = 0;
        if (s_FontWidth != null) {
            fontwidth = Double.valueOf(ele.attributeValue("p_FontWidth")).intValue();
        }
        if (s_FontHeight != null) {
            fontheight = Double.valueOf(ele.attributeValue("p_FontHeight")).intValue();
        }
        String linecolor = "rgb(" + ele.attributeValue("lc") + ")";
        String fontfamily = ele.attributeValue("ff");
        int wm = Double.valueOf(ele.attributeValue("wm")).intValue();
        String writemode = "";
        String value = ele.attributeValue("ts");
        switch (wm) {
            case 1:
            case 17:
                // 字体起点从左上角转为左下角
                starty += fontheight;
                writemode = "lr";
                break;
            case 2:
            case 18:
                // 字体起点从左上角转为左下角
                startx += fontwidth / 2;
                writemode = "tb";
                break;
            default:
                // 字体起点从左上角转为左下角
                starty += fontheight;
                writemode = "lr";

        }
        String[] values = value.split("\n");
        for (int i = 0; i < values.length; i++) {
            Element text = parent.addElement("text");
            if (i > 0) {
                startx += fontsize;
            }
//			text.addAttribute("x", startx + "");
//			text.addAttribute("y", starty + "");
            text.addAttribute("font-size", fontsize + "");
            text.addAttribute("font-width", fontwidth + "");
            text.addAttribute("font-height", fontheight + "");
            text.addAttribute("font-family", fontfamily);
            text.addAttribute("writing-mode", writemode);
            text.addAttribute("stroke", linecolor);
            // 字体是否填充
            text.addAttribute("fill", linecolor);
            text.setText(values[i]);
            String center = (fontwidth / 2) + "," + (fontheight / 2);
            String transform = ele.attribute("tfr") != null
                    ? "translate(" + startx + "," + starty + ") " + ele.attributeValue("tfr")
                    : "translate(" + startx + "," + starty + ")";
            text.addAttribute("transform", transByCenter(transform, center, center));
        }
        return parent;
    }

    private String colorConvert(String voltype) {
        if ("112871466063626241".equals(voltype)) {// 500kV
            return "rgb(255,0,0)";
        } else if ("112871466063626242".equals(voltype)) {// 220kV
            return "rgb(128,0,128)";
        } else if ("112871466063626243".equals(voltype)) {// 110kV
            return "rgb(240,65,80)";
        } else if ("112871466063626244".equals(voltype)) {// 35kV
            return "rgb(255,255,0)";
        } else if ("112871466063626245".equals(voltype)) {// 10kV
            return "rgb(185,72,66)";
        } else if ("112871466063626246".equals(voltype)) {// 6kV
            return "rgb(0,0,139)";
        } else if ("112871466063626248".equals(voltype)) {// 66kV
            return "rgb(255,204,0)";
        } else if ("112871466063626249".equals(voltype)) {// 330kV
            return "rgb(30,144,255)";
        } else if ("112871466063626250".equals(voltype)) {// 0.38kV
            return "rgb(85,255,255)";
        } else if ("112871466063626251".equals(voltype)) {// 1000kV
            return "rgb(0,0,255)";
        } else if ("112871466063626252".equals(voltype)) {// 20kV
            return "rgb(226,172,6)";
        } else if ("112871466063626253".equals(voltype)) {// 13kV
            return "rgb(0,210,0)";
        } else if ("112871466063626254".equals(voltype)) {// 15kV
            return "rgb(0,128,0)";
        } else if ("112871466063626257".equals(voltype)) {// 750kV
            return "rgb(250,128,10)";
        } else if ("112871466063626258".equals(voltype)) {// 800kV
            return "rgb(0,0,255)";
        } else {
            return "rgb(255,255,255)";
        }
    }

    private String transByCenter(String transform, String center, String center0) {
        String result = "";
        try {
            if (center == null || center0 == null) {
                return transform;
            } else {
                String centers[] = center.split(",");
                int center_x = Double.valueOf(centers[0]).intValue();
                int center_y = Double.valueOf(centers[1]).intValue();
                String center0s[] = center0.split(",");
                int center0_x = Double.valueOf(center0s[0]).intValue();
                int center0_y = Double.valueOf(center0s[1]).intValue();
                String[] transforms = transform.split(" ");
                String translate = "", rotate = "", scale = "";
                for (String temp : transforms) {
                    if (temp.indexOf("translate") != -1) {
                        translate = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
                    } else if (temp.indexOf("rotate") != -1) {
                        rotate = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
                    } else if (temp.indexOf("scale") != -1) {
                        scale = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
                    }
                }
                String[] scales = scale.split(",");
                double wfactor = Double.valueOf(scales[0]).doubleValue();
                double hfactor = Double.valueOf(scales[1]).doubleValue();
                double angle = Double.valueOf(rotate).doubleValue();
                String[] translates = translate.split(",");
                int x = Double.valueOf(translates[0]).intValue();
                int y = Double.valueOf(translates[1]).intValue();
                int pin_x = x + center_x;
                int pin_y = y + center_y;
                int pin0_x = x + center0_x;
                int pin0_y = y + center0_y;
                int new_pin_x = (int) ((pin_x - pin0_x) * wfactor + pin0_x);
                int new_pin_y = (int) ((pin_y - pin0_y) * hfactor + pin0_y);
                int dx = new_pin_x - pin0_x;
                int dy = new_pin_y - pin0_y;
                double tmpangle = angle * Math.PI / 180.0;
                int startx = (int) Math.round(dx * Math.cos(tmpangle) - dy * Math.sin(tmpangle) + pin0_x);
                int starty = (int) Math.round(dx * Math.sin(tmpangle) + dy * Math.cos(tmpangle) + pin0_y);
                if (!"".equals(translate)) {
                    result += "translate(" + Math.round(startx - center_x * wfactor) + ","
                            + Math.round(starty - center_y * hfactor) + ") ";
                }
                if (!"".equals(rotate)) {
                    result += "rotate(" + angle + "," + (center_x * wfactor) + "," + (center_y * hfactor) + ") ";
                }
                if (!"".equals(scale)) {
                    result += "scale(" + scale + ") ";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private String getTransform(String transform, String name) {
        if (StrUtil.isEmpty(transform)) {
            return "0";
        }
        String[] transforms = transform.split(" ");
        for (String temp : transforms) {
            if (temp.indexOf(name) != -1) {
                return temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
            }
        }
        return null;
    }
}
