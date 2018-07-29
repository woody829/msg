package cb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现一个固定长度的集合队列
 *
 * @author SHANHY(365384722@QQ.COM)
 * @date   2015年11月9日
 * @param <E>
 */
public class LimitQueue<E> implements Queue<E> {

    private static final Logger logger = LoggerFactory.getLogger(LimitQueue.class);

    /**
     * 队列长度，实例化类的时候指定
     */
    private int limit;

    Queue<E> queue = new LinkedList<E>();

    public LimitQueue(int limit){
        this.limit = limit;
    }

    /**
     * 入队
     */
    @Override
    public boolean offer(E e){
        if(queue.size() >= limit){
            //如果超出长度,入队时,先出队
            queue.poll();
        }
        return queue.offer(e);
    }

    /**
     * 出队
     */
    @Override
    public E poll() {
        return queue.poll();
    }

    /**
     * 获取队列
     *
     * @return
     * @author SHANHY
     * @date   2015年11月9日
     */
    public Queue<E> getQueue(){
        return queue;
    }

    /**
     * 获取限制大小
     *
     * @return
     * @author SHANHY
     * @date   2015年11月9日
     */
    public int getLimit(){
        return limit;
    }

    @Override
    public boolean add(E e) {
        return queue.add(e);
    }

    @Override
    public E element() {
        return queue.element();
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0 ? true : false;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public E remove() {
        return queue.remove();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return queue.addAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }


    //判断当前最高值和最低值超额不超过10
    public boolean jude( BigDecimal okPrice) {
        BigDecimal bmin = new BigDecimal(100000);
        BigDecimal bmax = new BigDecimal(0);
        BigDecimal bsum = new BigDecimal(0);
        BigDecimal pirce = new BigDecimal(0);
        BigDecimal first = new BigDecimal(0);
        if (queue.size() < limit) {
            logger.info("数据样本不足，不购买!");
            return false;
        }

        Iterator<E> listiterator = queue.iterator();//获得列表迭代器，扩展了Iterator接口

        int i = 0;

        while (listiterator.hasNext()) {//向后遍历

            pirce = (BigDecimal) listiterator.next();

            if (i == 0){
                first  =  pirce;
                i++;
            }

            if (bmin.compareTo(pirce) > 0) {
                bmin = pirce;
            }

            if (bmax.compareTo(pirce) < 0) {
                bmax = pirce;
            }

            bsum = bsum.add(pirce);

        }

        logger.info("maxOkPrice:"+bmax.toString()+",minOkPrice:"+bmin.toString()+",okPrice:"+okPrice);

        //差额不超过8
        if (bmax.subtract(bmin).compareTo(new BigDecimal(10)) >0) {
            logger.info("最大最小差超过8");
            if(bmax.subtract(okPrice).compareTo(new BigDecimal(4)) >0 ) {
                logger.info("最高价超过当前价大于4");
                return false;
            }
        }

        logger.info("first:"+first.toString()+",last:"+pirce.toString());

        if (first.subtract(okPrice).compareTo(new BigDecimal(2)) > 0  ) {
            logger.info("三分钟前价格比现在高2USDT，不够买！");
            return  false;
        }

        return true;

    }


}
