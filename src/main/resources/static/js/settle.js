/**
 * Created by CoderX on 2019/3/14
 */

var minUnitNum = 1;	//商品的最小单位数量

$(function (){
	
	//绑定全选按钮操作
	$("#checkAll").bind("click",function(){
		checkAll();
	});
	
	//选中全部商品计算结算金额
	$("#checkAll").click();
	
	//单个商品的勾选事件
	$(document).delegate(".checkbox_c","click",function(){
		singleCheck();
	});
	
});

//结算列表商品数量增加
function addGoods(goodsId){
	//step1:修改商品数量
	var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
	goodsNum = parseInt(goodsNum) + parseInt(minUnitNum);
	$("#" + goodsId + " input[name='goodsNum']").val(goodsNum);
	//step2:后续处理
	afterHandler();
}

//结算列表商品数量减少
function subtractGoods(goodsId){
	//step1:修改商品数量
	var goodsNum = $("#" + goodsId + " input[name='goodsNum']").val();
	if(parseInt(goodsNum) > parseInt(minUnitNum)){
		goodsNum = parseInt(goodsNum) - parseInt(minUnitNum);
		$("#" + goodsId + " input[name='goodsNum']").val(goodsNum);
		//step2:后续处理
		afterHandler();
	}
	
}

//结算列表商品变更以后执行的逻辑。例如：重新计算订单金额等等...
function afterHandler(){
	var selectNum = 0;
	var selectTotalPrice = 0;
	$(".checkbox_c").each(function(){
		var goodsNum = $("#" + $(this).val() + " input[name='goodsNum']").val();
		var goodsPrice = $("#goodsPrice_" + $(this).val()).html();
		//刷新单个商品总价
		$("#" + $(this).val() + " span.sum_price").html(parseFloat(parseInt(goodsNum)*parseFloat(goodsPrice)).toFixed(2));
		//统计选中商品的数量和金额
		if($(this).prop('checked')){//统计选中商品的数量和总价
			selectNum = parseInt(selectNum) + parseInt(goodsNum);
			selectTotalPrice = parseFloat(selectTotalPrice) + parseFloat(parseInt(goodsNum)*parseFloat(goodsPrice).toFixed(2));
		}
	});
	//刷新选中的商品数量和总金额
	$("#totalNum").html(selectNum);
	$("#totalMoney").html(parseFloat(selectTotalPrice).toFixed(2));
}

//结算列表全选按钮
function checkAll(){
	$(".checkbox_c").each(function(){
		$(this).prop("checked",$("#checkAll").prop('checked'));
	});
	//处理后续操作
	afterHandler();
}

//单个商品选中或取消勾选事件
function singleCheck(){
	//反选该商品
	$(this).prop("checked",$(this).prop("checked")?false:true);
	//商品变化后续处理逻辑
	afterHandler();
}

//提交订单
function submitOrder(){
	//step1:校验是否有要结算的商品,准备虚拟Ul数据
	var canSettleFlag = false;
	var userBalance = parseFloat($("#userBalance").html());
	var totalMoney = parseFloat($("#totalMoney").html());
	var quota = parseFloat($("#quota").val());
	if(userBalance < totalMoney){
		alert("用户可用余额不足！");
		return;
	}else if(quota < totalMoney){
		alert("下单金额超过用户限额，请调整下单商品金额以完成下单！");
		return;
	}else{
		var confirmSettle = window.confirm("确认提交订单！");
		if(!confirmSettle){
			return;
		}
	}
	var borrowUl = $("<ul></ul>");//将需要结算的商品组装的虚拟Ul中
	$(".checkbox_c").each(function(){
		if($(this).prop("checked")){
			canSettleFlag = true;
			$(borrowUl).append($("#" + $(this).val()).clone());
		}
	});
	//step2:提交购物车表单 虚拟表单提交
	if(canSettleFlag){

//		var tempForm = $("<form></form>");
//		$(tempForm).append(borrowUl);
//		$(tempForm).attr("action",ctx + "b2c/order/submitOrder").attr("method","POST").css("display","none");
//		$("body").append(tempForm);
//		$(tempForm).submit();

		var tempForm = $("<form></form>");
		$(tempForm).append(borrowUl);
		$.ajax({
	        type: "post",
	        url: ctx + "b2c/order/submitOrder",
	        data: $(tempForm).serialize(),
	        datatype:"json",
	        success: function(res) {
	        	//返回的分类列表展示出来
	        	if(res.resCode=="0"){
	        		alert(res.resMessage);
	        		window.location.href = ctx + "b2c/goods/goodsList";
	        	}else{
	        		alert(res.resMessage);
	        		window.location.href = ctx + "b2c/goods/goodsList";
	        	}
	        }
	    });
	}
	
}
