package fcoin.rest.stock;

import org.apache.http.HttpException;

import java.io.IOException;


/**
 * 现货行情，交易 REST API
 *
 * @author zhangchi
 */
public interface IStockRestApi {

    /**
     * 行情
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public String ticker(String symbol) throws HttpException, IOException;

    public String tickerFcoin(String symbol) throws HttpException, IOException;

    public String getDepthInfo(String symbol,String level) throws HttpException, IOException;

    /**
     * 市场深度
     *
     * @param symbol btc_usd:比特币    ltc_usd :莱特币
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public String depth(String symbol) throws HttpException, IOException;

    public String queryBalance() throws HttpException, IOException;



    /**
     * 获取用户信息
     *
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public String userinfo() throws HttpException, IOException;

    /**
     * 下单交易
     *
     * @param symbol btc_usd: 比特币 ltc_usd: 莱特币
     * @param type   买卖类型： 限价单（buy/sell） 市价单（buy_market/sell_market）
     * @param price  下单价格 [限价买单(必填)： 大于等于0，小于等于1000000 |
     *               市价买单(必填)： BTC :最少买入0.01个BTC 的金额(金额>0.01*卖一价) / LTC :最少买入0.1个LTC 的金额(金额>0.1*卖一价)]
     * @param amount 交易数量 [限价卖单（必填）：BTC 数量大于等于0.01 / LTC 数量大于等于0.1 |
     *               市价卖单（必填）： BTC :最少卖出数量大于等于0.01 / LTC :最少卖出数量大于等于0.1]
     * @return
     * @throws IOException
     * @throws HttpException
     */


    public String trade(String symbol, String type,
                        String price, String amount,String side) throws HttpException, IOException;


    public String getOrderList(String symbol,String states,String before,String after,String limit) throws HttpException, IOException;
    /**
     * 撤销订单
     *
     * @param order_id 订单ID(多个订单ID中间以","分隔,一次最多允许撤消3个订单)
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public String cancelOrder(String order_id) throws HttpException, IOException ;







}
