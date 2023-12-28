package com.rhy.note.jpinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.io.FileNotFoundException;

/**
 * @author: Rhy
 * @datetime: 2023/12/28 16:20
 * @description: 汉字转拼音
 */
public class JpinyinTestMain {
    public static void main(String[] args) throws PinyinException, FileNotFoundException {
        String str = "你好世界";
        System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK)); // nǐ,hǎo,shì,jiè
        System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER)); // ni3,hao3,shi4,jie4
        System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE)); // ni,hao,shi,jie
        System.out.println(PinyinHelper.getShortPinyin(str)); // nhsj
        System.out.println(PinyinHelper.getShortPinyin("A你好 C世界")); // nhsj
    }
}
