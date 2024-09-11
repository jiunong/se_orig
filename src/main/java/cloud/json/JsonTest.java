package cloud.json;

import cloud.model.NodeModel;
import cloud.model.SvgLinkModel;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/11/1 10:20
 */
public class JsonTest {

    private final static Logger logger = Logger.getLogger(JsonTest.class.getName());

    static List<String> tags = ListUtil.of("PD_30700000_763820", "PD_31100000_306157", "PD_30700000_763821", "PD_30700000_763822", "PD_30200002_341983" //辽宁省军区教导队11071 环网柜
            , "PD_30700000_1217977", "PD_31100000_539606", "PD_30700000_1217978", "PD_30700000_1218020", "PD_30700000_1218019", "PD_30700000_1217979", "PD_30700000_1218018" //金属线千山区消防救援大队环网柜
            , "PD_30700000_1218006", "PD_31100000_539667", "PD_30700000_1218007" //金属线千山区消防救援大队环网柜
            , "PD_30700000_1223435", "PD_31100000_542458", "PD_30700000_1223434", "PD_30700000_1223433" //用户变电站10106
            , "PD_30200002_619103", "PD_30700000_1223837", "PD_30200002_619104", "PD_30700000_1223838", "PD_30200002_619105", "PD_30700000_1223839", "PD_30200002_619102", "PD_30700000_1223836", "PD_30200002_619108", "PD_30700000_1223815", "PD_30200002_619109", "PD_30700000_1223856", "PD_30700000_1223857", "PD_30200002_619106", "PD_30700000_1223840", "PD_30200002_619107", "PD_30700000_1223841", "PD_31100000_542818", "PD_30700000_1223814", "PD_31100000_542817", "PD_30700000_1223755" //方舱医院
            , "PD_30700000_1223353", "PD_30700000_1223351", "PD_30700000_1223352", "PD_30700000_1223354", "PD_30700000_1223355", "PD_31100000_542425", "PD_30700000_1223350" //10kV解康线健康驿站环网柜
    );
    static List<String> saveTypes = ListUtil.of("SubStation", "Breaker");

    static int ids = 0;
    static int totalIds = 0;

    public static void main(String[] args) {

        String node = FileUtil.readString("C:\\Users\\76052\\Desktop\\node.json", StandardCharsets.UTF_8);
        //String node = FileUtil.readString("C:\\svg\\node.xml", StandardCharsets.UTF_8);
        NodeModel nodeModel = JSONObject.parseObject(node, NodeModel.class);
        //删除多余数据 留下有用的支线
        postVisit(nodeModel, null);
        List<String> collect = list.stream().filter(u -> ListUtil.of("鞍钢附企红钢水泥有限责任公司","鞍山冶金集团设备工程有限公司","鞍山市红力新型建筑材料制造有限公司","鞍钢附企机电设备制造有限公司").contains(u.getContainerName())).map(NodeModel::getValue).collect(Collectors.toList());
        //精简剩余支线关系
        preVisit(nodeModel, null);
        // 进行数据汇总,把环网柜组合在一起
        //inorderVisitUnit(nodeModel, null);
        inorderVisitBusBar(nodeModel, null);
        //删除多余的导线段
        inorderVisitClearACLine(nodeModel, null);
        initChildren(nodeModel);
        FileUtil.writeString(JSONObject.toJSONString(nodeModel, SerializerFeature.DisableCircularReferenceDetect), "C:\\svg\\node.xml", StandardCharsets.UTF_8);
        getLink();
    }


    static List<NodeModel> list = ListUtil.list(false);
    static List<SvgLinkModel> svgLinkModelList = ListUtil.list(false);


    /**
     * TODO 后序遍历 删除多余数据
     *
     * @param node
     * @param pNode
     */
    public static void postVisit(NodeModel node, NodeModel pNode) {
        totalIds++;
        if (node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        final CopyOnWriteArrayList<NodeModel> cowList = new CopyOnWriteArrayList<NodeModel>(children);
        for (int i = 0; i < cowList.size(); i++) {
            postVisit(cowList.get(i), node);
        }
        list.add(node);
        if (pNode != null && !tags.contains(node.getValue()) && (node.getChildren() == null || node.getChildren().size() == 0)) {
            pNode.removeChild(node);
            ids++;
        }
    }

    static boolean continueVisit = false;

    /**
     * TODO 前序遍历 从根节点开始 删除多余相邻节点
     */
    public static void preVisit(NodeModel node, NodeModel pNode) {
        if (continueVisit && node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        if (!saveTypes.contains(node.getType())     // 不在需要保留的设备范围内
                && (node.getChildren() != null && node.getChildren().size() < 2) //不是分叉节点（分叉不大于1的节点）
                && !tags.contains(node.getValue()) //  不在新增设备范围之内
                && (node.getChildren().stream().noneMatch(u -> tags.contains(u.getValue())))) { //子节点没有新增节点
            continueVisit = true;
            pNode.removeChild(node);
            pNode.appendChildren(children);
        }
        NodeModel tpNode = node;
        if (continueVisit) {
            tpNode = pNode;
            continueVisit = false;
        }

        children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        final CopyOnWriteArrayList<NodeModel> cowList = new CopyOnWriteArrayList<NodeModel>(children);

        for (NodeModel nodeModel : cowList) {
            preVisit(nodeModel, tpNode);
        }
    }

    /**
     * TODO 中序遍历 构建环网柜  进行数据汇总,把环网柜组合在一起
     */
    public static void inorderVisitUnit(NodeModel node, NodeModel pNode) {
        if (node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        final CopyOnWriteArrayList<NodeModel> cowList = new CopyOnWriteArrayList<NodeModel>(children);
        if (cowList.size() > 1 && node.getContainerType().equals("Substation")) {

            if (node.getContainerName().equals(pNode.getName())) {  //双母线 变电站名称一致 直接连母线
                NodeModel busbarSection = cowList.stream().filter(u -> u.getType().equals("BusbarSection")).findFirst().get(); //取出母线
                cowList.remove(busbarSection); //移除母线
                busbarSection.appendChild(node); //母线节点上移
                busbarSection.appendChildren(cowList);//母线节点上移
                pNode.removeChild(node);//删除原来子节点 连接变电站
                pNode.appendChild(busbarSection);
                node.setChildren(ListUtil.list(false));
                node = busbarSection;
            } else {
                NodeModel busbarSection = cowList.stream().filter(u -> u.getType().equals("BusbarSection")).findFirst().get(); //取出母线
                cowList.remove(busbarSection); //移除母线
                // 构建变电站节点
                String string = UUID.randomUUID().toString();
                NodeModel subStation = NodeModel.builder().id(string).value(string).containerName(pNode.getContainerName()).type(node.getContainerType()).name(node.getContainerName()).build();
                busbarSection.appendChild(node); //母线节点上移
                busbarSection.appendChildren(cowList);//母线节点上移
                subStation.appendChild(busbarSection);//变电站节点上移
                pNode.removeChild(node);//删除原来子节点 连接变电站
                pNode.appendChild(subStation);
                node.setChildren(ListUtil.list(false));
                node = subStation;
            }
        }
        for (NodeModel nodeModel : cowList) {
            inorderVisitUnit(nodeModel, node);
        }
    }
    /**
     * TODO 中序遍历 构建环网柜  进行数据汇总,把母线提上来
     */
    public static void inorderVisitBusBar(NodeModel node, NodeModel pNode) {
        if (node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        final CopyOnWriteArrayList<NodeModel> cowList = new CopyOnWriteArrayList<NodeModel>(children);
        if (cowList.size() > 1 && node.getContainerType().equals("Substation")) {
            NodeModel busbarSection = cowList.stream().filter(u -> u.getType().equals("BusbarSection")).findFirst().get(); //取出母线
            cowList.remove(busbarSection); //移除母线
            busbarSection.appendChildren(cowList);//母线节点上移
            node.setChildren(ListUtil.list(false));
            node.appendChild(busbarSection);
        }
        for (NodeModel nodeModel : cowList) {
            inorderVisitBusBar(nodeModel, node);
        }
    }

    public static void inorderVisitClearACLine(NodeModel node, NodeModel pNode) {
        if (node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        final CopyOnWriteArrayList<NodeModel> cowList = new CopyOnWriteArrayList<NodeModel>(children);
        if (node.getType().equals("ACLineSegment")) {
            pNode.removeChild(node);
            pNode.appendChildren(node.getChildren());
            node = cowList.get(0);
        }
        for (NodeModel nodeModel : cowList) {
            inorderVisitClearACLine(nodeModel, node);
        }
    }


    public static void initChildren(NodeModel node) {
        node.setId(node.getValue());
        List<NodeModel> children = node.getChildren();
        if (children == null) {
            return;
        }
        children.forEach(u -> {
            u.setParentId(node.getId());
            initChildren(u);
        });

    }


    static void getLink() {

        //String path = "C:\\Users\\76052\\Desktop\\金属线简图.xml";
        String path = "C:\\svg\\node.xml";
        String s = FileUtil.readString(path, StandardCharsets.UTF_8);
        NodeModel nodeModel = JSONObject.parseObject(s, NodeModel.class);
        getLinkNode(nodeModel);
        System.out.println(svgLinkModelList);
        FileUtil.writeString(JSONObject.toJSONString(svgLinkModelList, SerializerFeature.DisableCircularReferenceDetect), "C:\\svg\\link.xml", StandardCharsets.UTF_8);
    }

    static void getLinkNode(NodeModel node) {
        if (node == null) {
            return;
        }
        List<NodeModel> children = node.getChildren();
        children = children == null ? ListUtil.list(false) : children;
        for (int i = 0; i < children.size(); i++) {
            svgLinkModelList.add(SvgLinkModel.builder().beginNode(node.getId()).beginName(node.getName())
                    .endNode(children.get(i).getId()).endName(children.get(i).getName()).build());
            System.out.println(node.getName() + children.get(i).getName());
            getLinkNode(children.get(i));
        }


    }

    static void test1() {
        List<HashMap<String, Object>> list = ListUtil.list(false);

        HashMap<String, Object> objectObjectHashMap = MapUtil.newHashMap(false);
        objectObjectHashMap.put("A", "1");
        objectObjectHashMap.put("A1", "2");
        objectObjectHashMap.put("A2", null);
        list.add(objectObjectHashMap);

        HashMap<String, Object> objectObjectHashMap2 = MapUtil.newHashMap(false);
        objectObjectHashMap2.put("A", "1");
        objectObjectHashMap2.put("A1", null);
        objectObjectHashMap2.put("A2", null);
        list.add(objectObjectHashMap2);


        list.forEach(li -> {
            li.keySet().forEach(key -> {
                li.putIfAbsent(key, "");
            });
        });
        System.out.println(JSONUtil.toJsonStr(list));


    }


    static void main2(String[] args) {
        String s = "{\"date\":\"2021-10-31\",\"code\":\"CODE_1539827726031_11594\",\"method\":\"getPlant\",\"user\":{\"ip\":\"10.21.14.216\",\"roles\":\"ROLE_1571390085343_66671\",\"name\":\"testdc\",\"id\":\"testdc\"}}";
        String s2 = "{\n" + "\t\"errors\":\"成功\",\n" + "\t\"message\":\"成功\",\n" + "\t\"result\":{\n" + "\t\t\"xl\":{\n" + "\t\t\t\"current\":1,\n" + "\t\t\t\"orders\":[],\n" + "\t\t\t\"pages\":2292,\n" + "\t\t\t\"records\":[\n" + "\t\t\t\t{\n" + "\t\t\t\t\t\"assets\":[\n" + "\t\t\t\t\t\t{\n" + "\t\t\t\t\t\t\t\"astNature\":\"03\",\n" + "\t\t\t\t\t\t\t\"source#Name\":\"设备增加-技术改造\",\n" + "\t\t\t\t\t\t\t\"source\":\"02\",\n" + "\t\t\t\t\t\t\t\"astOrg#Name\":\"国网铁岭供电公司\",\n" + "\t\t\t\t\t\t\t\"astOrg\":\"14383AB1BA50422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\t\"astOrgName\":\"国网铁岭供电公司\",\n" + "\t\t\t\t\t\t\t\"matchingState\":\"01\",\n" + "\t\t\t\t\t\t\t\"ctime\":\"2017-05-24 00:00:00\",\n" + "\t\t\t\t\t\t\t\"astNature#Name\":\"省（直辖市、自治区）公司\",\n" + "\t\t\t\t\t\t\t\"equipCode\":\"22M00000296947459\",\n" + "\t\t\t\t\t\t\t\"isPrint\":\"0\",\n" + "\t\t\t\t\t\t\t\"astId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" + "\t\t\t\t\t\t\t\"deployState#Name\":\"退役\",\n" + "\t\t\t\t\t\t\t\"projectNum\":\"20170524\",\n" + "\t\t\t\t\t\t\t\"projectName\":\"分段线路\",\n" + "\t\t\t\t\t\t\t\"deployState\":\"30\"\n" + "\t\t\t\t\t\t}\n" + "\t\t\t\t\t],\n" + "\t\t\t\t\t\"distribution\":0,\n" + "\t\t\t\t\t\"id\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" + "\t\t\t\t\t\"modelId\":\"xl\",\n" + "\t\t\t\t\t\"resource\":{\n" + "\t\t\t\t\t\t\"erectionMethod#Name\":\"架空\",\n" + "\t\t\t\t\t\t\"maintOrg\":\"14383AB1BA2C422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"feeder\":\"22DKX-77441\",\n" + "\t\t\t\t\t\t\"voltageLevel#Name\":\"交流10kV\",\n" + "\t\t\t\t\t\t\"supplyArea\":\"05\",\n" + "\t\t\t\t\t\t\"overheadMethod\":\"1\",\n" + "\t\t\t\t\t\t\"supplyArea#Name\":\"D\",\n" + "\t\t\t\t\t\t\"startPosition\":\"5D23F3C0-144E-4A53-8613-486E99B58BD4-73733\",\n" + "\t\t\t\t\t\t\"pubPrivFlag#Name\":\"运检\",\n" + "\t\t\t\t\t\t\"isCommission#Name\":\"否\",\n" + "\t\t\t\t\t\t\"regionalism#Name\":\"农村\",\n" + "\t\t\t\t\t\t\"erectionMethod\":\"1\",\n" + "\t\t\t\t\t\t\"colorCode#Name\":\"黄色\",\n" + "\t\t\t\t\t\t\"dispatchLevel\":\"06\",\n" + "\t\t\t\t\t\t\"isRural\":\"1\",\n" + "\t\t\t\t\t\t\"dispatchLevel#Name\":\"县调\",\n" + "\t\t\t\t\t\t\"isRural#Name\":\"是\",\n" + "\t\t\t\t\t\t\"ctime\":\"2017-05-24 00:00:00\",\n" + "\t\t\t\t\t\t\"feeder#Name\":\"坝墙子变A43010kV坝长线\",\n" + "\t\t\t\t\t\t\"relateLineId\":\"26447FDC-45F7-4F96-ACD5-0925DCF9235A-09023\",\n" + "\t\t\t\t\t\t\"equipmentOwner\":\"E4AB99E4F8C46FF6E0430100007F1CB9\",\n" + "\t\t\t\t\t\t\"isCommission\":\"0\",\n" + "\t\t\t\t\t\t\"startType\":\"2\",\n" + "\t\t\t\t\t\t\"overheadMethod#Name\":\"单辐射\",\n" + "\t\t\t\t\t\t\"psrId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" + "\t\t\t\t\t\t\"astId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" + "\t\t\t\t\t\t\"maintOrg#Name\":\"国网盘山县供电公司\",\n" + "\t\t\t\t\t\t\"mainLine\":\"26447FDC-45F7-4F96-ACD5-0925DCF9235A-09023\",\n" + "\t\t\t\t\t\t\"name\":\"10kV坝长线环网分\",\n" + "\t\t\t\t\t\t\"lineNature#Name\":\"分段线路\",\n" + "\t\t\t\t\t\t\"overheadLength\":0.1397,\n" + "\t\t\t\t\t\t\"cableLength\":0.0000,\n" + "\t\t\t\t\t\t\"psrState\":\"30\",\n" + "\t\t\t\t\t\t\"regionalism\":\"05\",\n" + "\t\t\t\t\t\t\"city\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"importance\":\"1\",\n" + "\t\t\t\t\t\t\"importance#Name\":\"一般\",\n" + "\t\t\t\t\t\t\"runDevName\":\"10kV坝长线环网分\",\n" + "\t\t\t\t\t\t\"psrState#Name\":\"退役\",\n" + "\t\t\t\t\t\t\"startTime\":\"2017-05-24 00:00:00\",\n" + "\t\t\t\t\t\t\"pubPrivFlag\":\"0\",\n" + "\t\t\t\t\t\t\"startSwitch\":\"0\",\n" + "\t\t\t\t\t\t\"startType#Name\":\"杆塔\",\n" + "\t\t\t\t\t\t\"maintGroup#Name\":\"配电运检班\",\n" + "\t\t\t\t\t\t\"maintGroup\":\"14383AB1BA32422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"length\":0.1397,\n" + "\t\t\t\t\t\t\"lineNature\":\"4\",\n" + "\t\t\t\t\t\t\"city#Name\":\"国网盘锦供电公司\",\n" + "\t\t\t\t\t\t\"voltageLevel\":\"22\",\n" + "\t\t\t\t\t\t\"colorCode\":\"01\",\n" + "\t\t\t\t\t\t\"equipmentOwner#Name\":\"张艳华\"\n" + "\t\t\t\t\t}\n" + "\t\t\t\t},\n" + "\t\t\t\t{\n" + "\t\t\t\t\t\"assets\":[\n" + "\t\t\t\t\t\t{\n" + "\t\t\t\t\t\t\t\"astNature\":\"03\",\n" + "\t\t\t\t\t\t\t\"source#Name\":\"设备增加-基本建设\",\n" + "\t\t\t\t\t\t\t\"remark\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07711\",\n" + "\t\t\t\t\t\t\t\"source\":\"01\",\n" + "\t\t\t\t\t\t\t\"astOrg#Name\":\"国网盘锦供电公司\",\n" + "\t\t\t\t\t\t\t\"astOrg\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\t\"astOrgName\":\"国网盘锦供电公司\",\n" + "\t\t\t\t\t\t\t\"matchingState\":\"01\",\n" + "\t\t\t\t\t\t\t\"ctime\":\"1999-11-14 00:00:00\",\n" + "\t\t\t\t\t\t\t\"astNature#Name\":\"省（直辖市、自治区）公司\",\n" + "\t\t\t\t\t\t\t\"equipCode\":\"22M00000050865018\",\n" + "\t\t\t\t\t\t\t\"isPrint\":\"0\",\n" + "\t\t\t\t\t\t\t\"astId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" + "\t\t\t\t\t\t\t\"astNum\":\"160100006588\",\n" + "\t\t\t\t\t\t\t\"deployState#Name\":\"在运\",\n" + "\t\t\t\t\t\t\t\"deployState\":\"20\"\n" + "\t\t\t\t\t\t}\n" + "\t\t\t\t\t],\n" + "\t\t\t\t\t\"distribution\":0,\n" + "\t\t\t\t\t\"id\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" + "\t\t\t\t\t\"modelId\":\"xl\",\n" + "\t\t\t\t\t\"resource\":{\n" + "\t\t\t\t\t\t\"erectionMethod#Name\":\"架空\",\n" + "\t\t\t\t\t\t\"dispatchOrg\":\"8a2081325740817101574553fc220bc1\",\n" + "\t\t\t\t\t\t\"maintOrg\":\"14383AB1BA2C422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"feeder\":\"22DKX-90625\",\n" + "\t\t\t\t\t\t\"voltageLevel#Name\":\"交流10kV\",\n" + "\t\t\t\t\t\t\"supplyArea\":\"05\",\n" + "\t\t\t\t\t\t\"overheadMethod\":\"2\",\n" + "\t\t\t\t\t\t\"supplyArea#Name\":\"D\",\n" + "\t\t\t\t\t\t\"startPosition\":\"BA4D2309-98D7-4A16-8C5D-D8CDEECD5B49-00043\",\n" + "\t\t\t\t\t\t\"pubPrivFlag#Name\":\"运检\",\n" + "\t\t\t\t\t\t\"isCommission#Name\":\"否\",\n" + "\t\t\t\t\t\t\"regionalism#Name\":\"乡镇\",\n" + "\t\t\t\t\t\t\"erectionMethod\":\"1\",\n" + "\t\t\t\t\t\t\"colorCode#Name\":\"黄色\",\n" + "\t\t\t\t\t\t\"dispatchLevel\":\"03\",\n" + "\t\t\t\t\t\t\"isRural\":\"1\",\n" + "\t\t\t\t\t\t\"dispatchLevel#Name\":\"省（直辖市、自治区）调\",\n" + "\t\t\t\t\t\t\"isRural#Name\":\"是\",\n" + "\t\t\t\t\t\t\"ctime\":\"1999-11-14 00:00:00\",\n" + "\t\t\t\t\t\t\"startStation\":\"46BE93E3-9601-475D-9F08-A3F2F5E3EFC6-00015\",\n" + "\t\t\t\t\t\t\"feeder#Name\":\"陈家变F73410kV陈四线\",\n" + "\t\t\t\t\t\t\"equipmentOwner\":\"E4AB99E4F8C46FF6E0430100007F1CB9\",\n" + "\t\t\t\t\t\t\"isCommission\":\"0\",\n" + "\t\t\t\t\t\t\"startType\":\"1\",\n" + "\t\t\t\t\t\t\"overheadMethod#Name\":\"单联络\",\n" + "\t\t\t\t\t\t\"psrId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" + "\t\t\t\t\t\t\"startStation#Name\":\"陈家66kV变电站\",\n" + "\t\t\t\t\t\t\"astId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" + "\t\t\t\t\t\t\"maintOrg#Name\":\"国网盘山县供电公司\",\n" + "\t\t\t\t\t\t\"name\":\"陈家变F734 10kV陈四线\",\n" + "\t\t\t\t\t\t\"lineNature#Name\":\"馈线\",\n" + "\t\t\t\t\t\t\"overheadLength\":12.2873,\n" + "\t\t\t\t\t\t\"cableLength\":0.0000,\n" + "\t\t\t\t\t\t\"psrState\":\"20\",\n" + "\t\t\t\t\t\t\"regionalism\":\"06\",\n" + "\t\t\t\t\t\t\"city\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"runDevName\":\"1232\",\n" + "\t\t\t\t\t\t\"psrState#Name\":\"在运\",\n" + "\t\t\t\t\t\t\"startTime\":\"2015-07-11 00:00:00\",\n" + "\t\t\t\t\t\t\"pubPrivFlag\":\"0\",\n" + "\t\t\t\t\t\t\"startSwitch\":\"陈四线F734断路器\",\n" + "\t\t\t\t\t\t\"transferMark\":\"3\",\n" + "\t\t\t\t\t\t\"startType#Name\":\"间隔\",\n" + "\t\t\t\t\t\t\"maintGroup#Name\":\"配电运检班\",\n" + "\t\t\t\t\t\t\"maintGroup\":\"14383AB1BA32422DE0541CC1DE1077D5\",\n" + "\t\t\t\t\t\t\"length\":12.2873,\n" + "\t\t\t\t\t\t\"dispatchOrg#Name\":\"国网盘锦供电公司电力调度控制中心\",\n" + "\t\t\t\t\t\t\"lineNature\":\"5\",\n" + "\t\t\t\t\t\t\"city#Name\":\"国网盘锦供电公司\",\n" + "\t\t\t\t\t\t\"voltageLevel\":\"22\",\n" + "\t\t\t\t\t\t\"colorCode\":\"01\",\n" + "\t\t\t\t\t\t\"equipmentOwner#Name\":\"张艳华\"\n" + "\t\t\t\t\t}\n" + "\t\t\t\t}\n" + "\t\t\t],\n" + "\t\t\t\"searchCount\":true,\n" + "\t\t\t\"size\":20,\n" + "\t\t\t\"total\":45833\n" + "\t\t}\n" + "\t},\n" + "\t\"status\":\"000000\"\n" + "}";
        JSONObject jsonObject = JSONObject.parseObject(s2);
        JSONArray dataList = jsonObject.getJSONObject("result").getJSONObject("xl").getJSONArray("records");
        for (int i = 0; i < dataList.size(); i++) {
            int finalI = i;
            dataList.getJSONObject(i).getJSONObject("resource").forEach((k, v) -> {
                if (k.contains("#")) {
                    dataList.getJSONObject(finalI).remove(k);
                    dataList.getJSONObject(finalI).put(k.replaceAll("#", ""), v);
                }
            });
        }
        System.out.println(jsonObject.get("access_token"));
    }


}
