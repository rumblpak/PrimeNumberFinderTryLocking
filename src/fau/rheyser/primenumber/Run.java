package fau.rheyser.primenumber;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.os.Message;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;

public class Run extends Activity {
	private static final String TAG = "Primes";
	
	public static String numberthreads = "fau.rheyser.primenumber.threads";
	
	public String primes = "";
	public int numPrimes = 0;
	
	public static final int threads_one = 1;
	public static final int threads_two = 2;
	public static final int threads_three = 3;
	public static final int threads_four = 4;
	
	public long time1,time2;
	
	private Thread th1;
	private Thread th2;
	private Thread th3;
	private Thread th4;
	
	private Long timelimit = 5L;
	
	private Lock lock = new ReentrantLock();
	//private Lock handlerlock = new ReentrantReadWriteLock().writeLock();
	
	private int thread;
	private static TextView txt;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.d(TAG, "onSendMessage: " + msg.obj.toString());
			//handlerlock.lock();
			txt = (TextView)findViewById(R.id.primes);
			//txt.setText(txt.getText()+primes+System.getProperty("line.separator"));
			//txt.setText("" + msg.obj.toString() + System.getProperty("line.separator"));
			txt.setText(txt.getText() + msg.obj.toString() + "\n");
			txt.setMovementMethod(new ScrollingMovementMethod());
			//handlerlock.unlock();
		}
	};
	
	private Message msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreateRun");
		int threads = getIntent().getIntExtra(numberthreads, 0);
		thread = primeHelper(threads);
		
		super.setContentView(R.layout.run);
		
		if(thread == 1) {
			Log.d(TAG, "onThread = 1");
			th1 = new Thread(new Runnable() {
				public void run(){
					for(int i = 0; i < 10000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th1.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
			});
			time1 = java.lang.System.currentTimeMillis();
			th1.start();
			while(th1.isAlive());
			time2 = java.lang.System.currentTimeMillis();
			msg = handler.obtainMessage();
			msg.obj = primes;
			handler.sendMessage(msg);
		}
		else if(thread == 2) {
			Log.d(TAG, "onThread = 2");
			th1 = new Thread(new Runnable() {
				public void run(){
					for(int i = 0; i < 5000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th1.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			th2 = new Thread(new Runnable() {
				public void run(){
					for(int i =5001; i < 10000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th2.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			time1 = java.lang.System.currentTimeMillis();
			th1.start();
			th2.start();
			//while(th1.isAlive() && th2.isAlive());
			//Must check in reverse order because isAlive freezes in the emulator
			while(th2.isAlive());
			while(th1.isAlive());
			time2 = java.lang.System.currentTimeMillis();
			msg = handler.obtainMessage();
			msg.obj = primes;
			handler.sendMessage(msg);
		}
		else if(thread == 3) {
			Log.d(TAG, "onThread = 3");
			th1 = new Thread(new Runnable() {
				public void run(){
					for(int i = 0; i < 3333 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th1.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			th2 = new Thread(new Runnable() {
				public void run(){
					for(int i =3334; i < 6666 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th2.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			th3 = new Thread(new Runnable() {
				public void run(){
					for(int i =6667; i < 10000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th3.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			time1 = java.lang.System.currentTimeMillis();
			th1.start();
			th2.start();
			th3.start();
			//while(th1.isAlive() && th2.isAlive() && th3.isAlive());
			//Must check in reverse order because isAlive freezes in the emulator
			while(th3.isAlive());
			while(th2.isAlive());
			while(th1.isAlive());
			time2 = java.lang.System.currentTimeMillis();
			msg = handler.obtainMessage();
			msg.obj = primes;
			handler.sendMessage(msg);
		}
		else if(thread == 4) {
			Log.d(TAG, "onThread = 4");
			th1 = new Thread(new Runnable() {
				public void run(){
					for(int i = 0; i < 2500 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th1.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			th2 = new Thread(new Runnable() {
				public void run(){
					for(int i =2501; i < 5000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th2.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
			});
			th3 = new Thread(new Runnable() {
				public void run(){
					for(int i =5001; i < 7500 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th3.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			th4 = new Thread(new Runnable() {
				public void run(){
					for(int i = 7501; i < 10000 ; i++) {
						if(IsPrime.isPrime(i)) {
							//protect primes with trylocking with a Long.MAX_VALUE timelimit for catching the lock
							
							try {
								if(lock.tryLock() || lock.tryLock(timelimit, TimeUnit.MILLISECONDS) )
								{
									try {
										primes = primes + String.valueOf(i) + "\n";
										numPrimes++;
									}

									finally {
										lock.unlock();
									}
								}
								else {
									--i;
									th4.yield();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
			time1 = java.lang.System.currentTimeMillis();
			th1.start();
			th2.start();
			th3.start();
			th4.start();
			//while(th1.isAlive() && th2.isAlive() && th3.isAlive() && th4.isAlive());
			//Must check in reverse order because isAlive freezes in the emulator
			while(th4.isAlive());
			while(th3.isAlive());
			while(th2.isAlive());
			while(th1.isAlive());
			time2 = java.lang.System.currentTimeMillis();
			msg = handler.obtainMessage();
			msg.obj = primes;
			handler.sendMessage(msg);
		}
		else {
			Log.d(TAG, "Thread does not equal 1,2,3,4");
		}
		
		//Time in Seconds
		long time = (time2 - time1);
		//primes = "Time to generate in (ms): " + String.valueOf(time);
		msg = handler.obtainMessage();
		msg.obj = "Time to generate in (ms): " + String.valueOf(time);
		handler.sendMessage(msg);
		
		msg = handler.obtainMessage();
		msg.obj = "Number of Primes Found: " + String.valueOf(numPrimes);
		handler.sendMessage(msg);
		numPrimes = 0;
	}
	
	private int primeHelper(int threads) {
		//Log.d(TAG, "PrimeHelper");
		//Log.d(TAG, String.valueOf(threads));
		int th;
		switch(threads+1) {
		case threads_one:
			th = 1;
			break;
		case threads_two:
			th = 2;
			break;
		case threads_three:
			th = 3;
			break;
		case threads_four:
			th = 4;
			break;
		default:
			Log.d(TAG, "Defaulting in primeHelper");
			th = 1;
			break;
		}
		return th;
	}

}

