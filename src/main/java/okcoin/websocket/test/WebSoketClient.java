package okcoin.websocket.test;


import okcoin.websocket.WebSocketBase;
import okcoin.websocket.WebSocketService;
/**
 * 通过继承WebSocketBase创建WebSocket客户端
 * @author okcoin
 *
 */
public class WebSoketClient extends WebSocketBase{
	public WebSoketClient(String url,WebSocketService service){
		super(url,service);
	}
}
