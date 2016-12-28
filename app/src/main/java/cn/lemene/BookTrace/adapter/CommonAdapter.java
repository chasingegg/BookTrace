package cn.lemene.BookTrace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
    protected List<T> mList;
    
    public CommonAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }
    
    @Override
    public int getCount() {
        return mList.size();
    }
    
    @Override
    public T getItem(int position) {
        return mList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder<T> holder;
        if (convertView == null) {
            // 抽象 getLayout() 方法来指定 UI 布局文件
            convertView = LayoutInflater.from(mContext).inflate(getLayout(), parent, false);
        
            // 抽象初始化 UI 组件方法
            holder = createViewHolder();
            holder.view = convertView;
            holder.initView();
            
            convertView.setTag(holder);
        } else {
            holder = (CommonViewHolder<T>) convertView.getTag();
        }
        
        T item = getItem(position);
        
        // 抽象事件监听方法
        holder.initListener(position, item);
        
        // 抽象 UI 组件更新方法
        holder.updateView(position, item);
        return convertView;
    }
    
    /**
     * 创建 MyViewHolder 对象
     * @return MyViewHolder 对象
     */
    protected abstract CommonViewHolder<T> createViewHolder();
    
    /**
     * 绑定 ListView Item 的布局文件
     * @return 布局文件的资源 ID
     */
    protected abstract int getLayout();
}
