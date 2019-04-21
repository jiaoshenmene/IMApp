package net.qiujuer.italker.common.widget.recycler;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019/4/21 7:47 PM
 */

public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
