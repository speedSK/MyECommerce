/**
 * Created by CoderX on 2019/3/15
 */
$(function (){
	//进入页面获取分类栏信息
	asynGetGoodsCategoryList("");
	
	//进入页面获取全面商品
	asynGetGoodsList("");
	
	//为商品筛选栏的“X”和全部撤销绑定清空已选分类事件
	$("body").on("click", "#clearAllCondition,.close-icon-event", function() {
		//step1:清空商品筛选栏的已选分类
		clearCategory();
		//step2:重新刷新分类列表
		asynGetGoodsCategoryList("");
		//step3:查询全部的商品
		asynGetGoodsList("");
    });
});

/**
 * 异步获取分类列表(根据分类ID获取下一级分类列表HTML)
 */
function asynGetGoodsCategoryList(categoryId){
	//发起ajax请求获取分类列表
	$.ajax({
        type: "post",
        url: ctx + "business/goodCategory/categoryList",
        data: {
            "parentId": categoryId
        },
        success: function(res) {
        	//返回的分类列表展示出来
        	$(".category-dd-event").html(res);
        }
    });
}


/**
 * 异步根据分类获取商品列表
 * 分类为Blank搜索所有商品
 */
function asynGetGoodsList(categoryId){
	//发起ajax请求获取商品列表
	$.ajax({
        type: "post",
        url: ctx + "b2c/goods/goodsInfo",
        data: {
            "categoryId": categoryId
        },
        success: function(res) {
        	//返回的商品列表展示出来
        	$("#goodsTableDiv").html(res);
        	//根据屏幕高度设置商品列表.goodsDiv的高度 650px;
	    	$(".goodsDiv").css("height",$(window).height());
        }
    });
}

/**
 *	选择分类时执行逻辑
 *	param:obj(单击的分类对象)
 */
function selectCategory(obj){
	//step1:清空之前的分类
	clearCategory();
	//step2:选择操作的分类
	var a = madeCategoryLink(obj);
	$(".category-div").append(a);
	//step3:显示"全部撤销"按钮
	showAllRepeal(true);
	//step4:根据新的分类ID查询该分类下的所有商品
	asynGetGoodsList($(obj).attr("categoryId"));
	//step5:根据新的分类获取子分类集合来刷新分类栏
	asynGetGoodsCategoryList($(obj).attr("categoryId"));
}

/**
 * 构建商品筛选栏分类链接标签
 * 触发对象应包含categoryId和categoryName两个属性
 * @returns 构建好的A标签（显示商品筛选分类的A标签）
 */
function madeCategoryLink(obj){
	var a = document.createElement("a");
	$(a).addClass("event-a").addClass("selected-category-event");
	$(a).attr("href","javascript:void(0)").attr("data-clear-item","categoryId").attr("categoryId",$(obj).attr("categoryId"));
	var i = document.createElement("i");
	$(i).addClass("close-icon").addClass("close-icon-event");
	$(a).append(i);
	$(a).append("分类：");
	var span = document.createElement("span");
	$(span).attr("name","categoryName");
	//插入要显示的分类名称
	$(span).html($(obj).attr("categoryName"));
	$(a).append(span);
	return a;
}

/**
 * 切换"全部撤销"按钮的显示/隐藏
 */
function showAllRepeal(flag){
	if(flag==true){
		$(".allRepeal").css("display","block");
	}else{
		$(".allRepeal").css("display","none");
	}
}

/**
 * 取消"分类筛选"或点击"全部撤销"按钮执行逻辑
 */
function clearCategory(){
	//step1:清除已有的分类
	$(".selected-category-event").remove();
	//step2:隐藏"全部撤销"按钮
	showAllRepeal(false);
}
