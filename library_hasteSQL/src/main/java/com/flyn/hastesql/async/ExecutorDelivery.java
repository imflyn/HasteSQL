package com.flyn.hastesql.async;

import android.os.Handler;

import java.util.concurrent.Executor;

public class ExecutorDelivery
{
    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler)
    {
        mResponsePoster = new Executor()
        {
            @Override
            public void execute(Runnable command)
            {
                handler.post(command);
            }
        };
    }

    public void sendStartMessage(final AsyncListener<?> listener)
    {
        if (listener != null)
            this.mResponsePoster.execute(new Runnable()
            {

                @Override
                public void run()
                {
                    listener.onStart();
                }
            });
    }

    public void sendFinishMessage(final AsyncListener<?> listener)
    {
        if (listener != null)
            this.mResponsePoster.execute(new Runnable()
            {

                @Override
                public void run()
                {
                    listener.onFinish();
                }
            });
    }

    public <T> void sendSuccessMessage(final AsyncListener<T> listener, final T result)
    {
        if (listener != null)

            this.mResponsePoster.execute(new Runnable()
            {

                @Override
                public void run()
                {
                    listener.onSuccess(result);
                }
            });
    }

    public void sendFailureMessage(final AsyncListener<?> listener, final Throwable error)
    {
        if (listener != null)
            this.mResponsePoster.execute(new Runnable()
            {

                @Override
                public void run()
                {
                    listener.onFailure(error);
                }
            });
    }


}
