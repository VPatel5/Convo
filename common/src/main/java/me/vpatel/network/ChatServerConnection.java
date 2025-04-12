package me.vpatel.network;

import com.google.common.base.MoreObjects;
import io.netty.channel.ChannelHandlerContext;
import me.vpatel.network.protocol.ChatServerPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;

public class ChatServerConnection {

    private static final Logger log = LogManager.getLogger(ChatServerConnection.class);

    private final ChannelHandlerContext ctx;


    public ChatServerConnection(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public SocketAddress getRemoteAddress() {
        return ctx.channel().remoteAddress();
    }

    public void sendPacket(ChatServerPacket packet) {
        ctx.channel().writeAndFlush(packet);
    }

    public void close(String reason) {
        ctx.channel().close();
    }

    public void clientClose() {
        ctx.channel().close();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("addr", getRemoteAddress())
                .toString();
    }
}
