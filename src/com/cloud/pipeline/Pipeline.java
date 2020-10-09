package com.cloud.pipeline;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/16 8:55
 */
public interface Pipeline {
    //void init(PipelineConfig config);
    void start();
    Context getContext();
}
