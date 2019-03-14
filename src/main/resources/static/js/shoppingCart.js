/**
 * Created by CoderX on 2018/8/6
 */
var cartWidth = 400;//购物车宽度
var minUnitNum = 1;	//商品的最小单位数量

$(function (){
	//绑定打开/关闭购物车事件
	$("#cartbar").bind("click",function(){
		if($(this).hasClass("active")){
			closeCart();
		}else{
			openCart();
		}
	});
	
	//绑定全选按钮操作
	$("#checkAll").bind("click",function(){
		checkAll();
	});
	
	//单个商品的勾选事件
	$(document).delegate(".checkbox_c","click",function(){
		singleCheck();
	});
	
	//根据屏幕高度设置购物车商品列表.cartUl的高度，153px是购物车商品栏以外的高度
	$(".cartUl").css("height",$(window).height()-153);
});

//打开购物车
function openCart() {
	//标识已经打开购物车
	$("#cartbar").addClass("active");
	//展开购物列表
	$(".toolbar_btn").css("left","-="+cartWidth);
};

//关闭购物车
function closeCart() {
	//标识已经关闭购物车
	$("#cartbar").removeClass("active");
	//隐藏购物列表
	$(".toolbar_btn").css("left","+="+cartWidth);
};

//添加商品到购物车
function addShoppingCart(id,name,price,image){
	var originalHtml = $("#cartUl").html();	//购物车原来的商品html
	//step1:判断是否已经包含该商品,包含则修改数量，不包含则增加商品
	var tempUl = document.createElement("ul");//购物车列表Ul替身，处理完应该将替身的innerHtml替换回cartUl中
	$(tempUl).html(originalHtml);
	var liList = tempUl.children;
	var hasGoodsFlag = false;//购物车中是否已经包含该商品标识
	for(var liIndex = 0; liIndex < liList.length; liIndex++){//每个li对应一个商品
		var goodsId = liList[liIndex].getAttribute("id");
		if(goodsId==id){//购物车中包含该商品则为该商品的数量加上该商品的最小单位数量
			hasGoodsFlag = true;
			var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
			goodsNum = parseInt(goodsNum) + parseInt(minUnitNum);
			$("#" + goodsId + " input[name='goodsNum']").val(goodsNum);
		}
	}
	if(!hasGoodsFlag){//判断购物车中是否包含该商品不包含则新增商品li
		//新增一个商品li
		var li = madeGoodsLi(id,name,image,price);
		$("#cartUl").append(li);
		li = null;//防止内存泄漏
	}
	//step2:商品变化后续处理逻辑
	afterHandler();
};

//从购物车移除商品
function delGoods(goodsId){
	//step1:获取商品数量，移除商品Li
	var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
	$("#" + goodsId).remove();
	//step2:商品变化后续处理逻辑
	afterHandler();
}

//购物车商品数量增加
function addGoods(goodsId){
	//step1:修改商品数量
	var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
	goodsNum = parseInt(goodsNum) + parseInt(minUnitNum);
	$("#" + goodsId + " input[name='goodsNum']").val(goodsNum);
	//step2:商品变化后续处理逻辑
	afterHandler();
}

//购物车商品数量减少
function subtractGoods(goodsId){
	//step1:修改商品数量
	var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
	if(parseInt(goodsNum) > parseInt(minUnitNum)){
		goodsNum = parseInt(goodsNum) - parseInt(minUnitNum);
		$("#" + goodsId + " input[name='goodsNum']").val(goodsNum);
		//step2:商品变化后续处理逻辑
		afterHandler();
	}
}

//购物车商品数量变更以后执行的逻辑。例如：刷新购物车logo上的数量...
function afterHandler(){
	var selectNum = 0;
	var selectTotalPrice = 0;
	$(".checkbox_c").each(function(){
		if($(this).prop('checked')){//统计选中商品的数量和总价
			var goodsNum = $("#" + $(this).val() + " input[name='goodsNum']").val();
			var goodsPrice = $("#goodsPrice_" + $(this).val()).val();
			selectNum = parseInt(selectNum) + parseInt(goodsNum);
			selectTotalPrice = parseFloat(selectTotalPrice) + parseFloat(parseInt(goodsNum)*parseFloat(goodsPrice));
		}
	});
	//刷新购物车logo上的数量和购物车商品总数
	$("#lmliCount").html(selectNum);
	$("#totalNum").html(selectNum);
	//刷新选中的商品总金额
	$("#totalPrice").html(parseFloat(selectTotalPrice).toFixed(2));
}

//构建购物车商品li,返回li标签的dom对象
function madeGoodsLi(goodsId,goodsName,goodsImg,goodsPrice){
	var li = document.createElement("li");
	$(li).addClass("clearfix").attr("id",goodsId);
	//checkInput
	var checkInput = document.createElement("input");
	$(checkInput).addClass("checkbox_c").attr({"name":"goodsId","type":"checkbox","checked":"checked"}).val(goodsId);
	$(li).append(checkInput);
	//goodsPriceHiddenInput
	var priceInput = document.createElement("input");
	$(priceInput).attr({"id":"goodsPrice_"+goodsId,"type":"hidden"}).val(goodsPrice);
	$(li).append(priceInput);
	//imgDiv
	var imgDiv = document.createElement("div");
	$(imgDiv).addClass("img_con");
	var imgLable = document.createElement("img");
	$(imgLable).addClass("img-circle").attr("src",goodsImg);
	$(imgDiv).append(imgLable);
	$(li).append(imgDiv);
	//productDiv
	var productDiv = document.createElement("div");
	$(productDiv).addClass("product_name");
	var productSpan = document.createElement("span");
	$(productSpan).attr("title",goodsName).html(goodsName);
	var priceSpan = document.createElement("span");
	$(priceSpan).addClass("price").addClass("fl").css("color","red").html("单价：¥" + goodsPrice);
	var delALable = document.createElement("a");
	$(delALable).addClass("del_pro_btn").attr("href","javascript:void(0)").attr("onclick","javascript:delGoods(\'"+goodsId+"\');").html("删除");
	$(productDiv).append(productSpan).append(priceSpan).append(delALable);
	$(li).append(productDiv);
	//amountDiv
	var amountDiv = document.createElement("div");
	$(amountDiv).addClass("amount_btn").addClass("clearfix");
	var amountSpinnerDiv = document.createElement("div");
	$(amountSpinnerDiv).addClass("spinner")
	var decreaseButton = document.createElement("button");
	$(decreaseButton).addClass("decrease").attr("type","button").attr("onclick","javascript:subtractGoods(\'"+goodsId+"\');").html("-");
	var numInput = document.createElement("input");
	$(numInput).addClass("spinnerExample").addClass("value").attr({"type":"text","name":"goodsNum","readonly":"readonly","maxlength":"2"}).val(minUnitNum);
	var increaseButton = document.createElement("button");
	$(increaseButton).addClass("increase").attr("type","button").attr("onclick","javascript:addGoods(\'"+goodsId+"\');").html("+");
	$(amountSpinnerDiv).append(decreaseButton).append(numInput).append(increaseButton);
	$(amountDiv).append(amountSpinnerDiv);
	$(li).append(amountDiv);
	return li;
}

//购物车全选按钮
function checkAll(){
	$(".checkbox_c").each(function(){
		$(this).prop("checked",$("#checkAll").prop('checked'));
	});
	//商品变化后续处理逻辑
	afterHandler();
}

//单个商品选中或取消勾选事件
function singleCheck(){
	//反选该商品
	$(this).prop("checked",$(this).prop("checked")?false:true);
	//商品变化后续处理逻辑
	afterHandler();
}

//购物车去结算
function settle(){
	//step1:校验是否有要结算的商品,准备虚拟Ul数据
	var canSettleFlag = false;
	var borrowUl = document.createElement("ul");//将需要结算的商品组装的虚拟Ul中
	$(".checkbox_c").each(function(){
		if($(this).prop("checked")){
			canSettleFlag = true;
			$(borrowUl).append($("#" + $(this).val()).clone());
		}
	});
	//step2:提交购物车表单 虚拟表单提交
	if(canSettleFlag){
		var tempForm = document.createElement("form");
		$(tempForm).append(borrowUl);
		$(tempForm).attr("action",ctx + "order/settlePage").attr("method","POST").css("display","none");
		$("body").append(tempForm);
		$(tempForm).submit();
	}
	
}
