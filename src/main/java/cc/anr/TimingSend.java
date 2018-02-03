package cc.anr;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class TimingSend  implements Runnable{

	public void run() {
 
		if(NettyClient.ctx!=null) {
			System.out.println("========================="+NettyClient.ctx.channel().isOpen());

			if(NettyClient.ctx.channel().isOpen()) {
			NettyClient.ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));  
			}else {
				
			}

		}
		

        
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.run();
		NettyClient.pool.execute(this);

	}

}
