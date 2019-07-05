package com.telek.elec.netty;

import com.telek.elec.protocal.Packet;
import com.telek.elec.protocal.apdu.CodecAPDU;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SyncWriteFuture implements Future<Packet> {
    private CountDownLatch latch = new CountDownLatch(1);
    private Packet result;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Packet get() throws InterruptedException {
        latch.wait();
        return result;
    }

    @Override
    public Packet get(long timeout, TimeUnit unit) throws InterruptedException {
        if (latch.await(timeout, unit)) {
            return result;
        }
        return null;
    }

    public void setResult(Packet result) {
        this.result = result;
        latch.countDown();
    }
}
