package cn.com.jinwang.ptn;

import java.util.List;

import com.google.common.collect.Lists;

public class ModelLoader<T> {
	
	private List<ModelLoader<T>> responsers = Lists.newArrayList();
	
	public  T load() {
		return null;
	}
	
	public void addResponser(ModelLoader<T> ml) {
		this.responsers.add(ml);
	}
}
