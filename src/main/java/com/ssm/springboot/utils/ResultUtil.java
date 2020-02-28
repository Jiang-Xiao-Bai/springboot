package com.ssm.springboot.utils;

import com.github.pagehelper.Page;
import com.ssm.springboot.enums.StatusCode;
import com.ssm.springboot.vo.PageResultVO;
import com.ssm.springboot.vo.ResultVO;


/**
 * Created by ziyi on 2018/7/5.
 */
public class ResultUtil {

    public static ResultVO success(Object object){
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResultVO error(String msg){
        ResultVO result = new ResultVO();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    public static ResultVO error(StatusCode code){
        ResultVO result = new ResultVO();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }


    public static ResultVO success (StatusCode code){
        ResultVO result = new ResultVO();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    public static ResultVO success(Object pageObjcet, Object list){
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setPages(((Page)pageObjcet).getPages());
        pageResultVO.setCurrent(((Page)pageObjcet).getPageNum());
        pageResultVO.setTotal(((Page)pageObjcet).getTotal());
        pageResultVO.setList(list);
        return success(pageResultVO);
    }


    public static ResultVO success(StatusCode code,Object data){
        ResultVO result = new ResultVO();
        result.setCode(code.getCode());
        result.setData(data);
        return result;
    }
}
