package cc.anr.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.sql.Date;

public class TimeClientHandler extends ChannelHandlerAdapter {


	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        
	        ByteBuf m = (ByteBuf) msg;
	        String content=m.toString(io.netty.util.CharsetUtil.UTF_8);
	        System.out.println(content);
	        m.release();
	      

	        
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
}
