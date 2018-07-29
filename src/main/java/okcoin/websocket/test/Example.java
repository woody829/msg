package okcoin.websocket.test;

import okcoin.websocket.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebSocket API使用事例
 * 
 * @author okcoin
 * 
 */
public class Example {
	private static final Logger logger = LoggerFactory.getLogger(Example.class);
	public static void main(String[] args) {
//        URL prop = Thread.currentThread().getContextClassLoader().getResource("properties/log4j.properties");
//        PropertyConfigurator.configure(prop);

		// apiKey 为用户申请的apiKey
		String apiKey = "XXXXX";

		// secretKey为用户申请的secretKey
		String secretKey = "XXXXX";

		apiKey = "dfbb23da-5cac-4bec-b4ce-ed828f8a5458";
		secretKey = "B0FA4F10E14364A0C7562DDFBBFD432C";

		// 国际站WebSocket地址 注意如果访问国内站 请将 real.okcoin.com 改为 real.okcoin.cn
		String url = "wss://real.okcoin.com:10440/websocket/okexapi";
//		String url = "wss://real.okcoin.com:10440/websocket/okcoinapi";

		// 订阅消息处理类,用于处理WebSocket服务返回的消息
		WebSocketService service = new BuissnesWebSocketServiceImpl();

		// WebSocket客户端
		WebSoketClient client = new WebSoketClient(url, service);

		// 启动客户端
		client.start();

        // 获取用户信息
        client.getUserInfo(apiKey,secretKey);

		// 添加订阅
		client.addChannel("ok_sub_spotusd_btc_ticker");
        //ok_sub_futureusd_btc_ticker_this_week   ok_sub_futureusd_btc_ticker_this_week

		// 删除定订阅
		// client.removeChannel("ok_sub_spotusd_btc_ticker");

		// 合约下单交易
//		 client.futureTrade(apiKey, secretKey, "btc_usd", "this_week", 2.3, 2,
//		 1, 0, 10);

		// 实时交易数据 apiKey
//		 client.futureRealtrades(apiKey, secretKey);

		// 取消合约交易
		// client.cancelFutureOrder(apiKey, secretKey, "btc_usd", 123456L,
		// "this_week");

		// 现货下单交易
		// client.spotTrade(apiKey, secretKey, "btc_usd", 3.2, 2.3, "buy");

		// 现货交易数据
		// client.realTrades(apiKey, secretKey);

		// 现货取消订单
		// client.cancelOrder(apiKey, secretKey, "btc_usd", 123L);


	}
}
