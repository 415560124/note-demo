package com.rhy.note.source;

/**
 * @author: Herion Lemon
 * @date: 2021年04月19日 16:47:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class DemoT extends DemoParentT<UseT,UseE> {
    @Override
    public Boolean getReturnType(){
        return false;
    }
}
