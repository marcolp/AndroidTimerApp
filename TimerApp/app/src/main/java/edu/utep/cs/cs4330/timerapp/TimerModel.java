package edu.utep.cs.cs4330.timerapp;

/** A simple model of a timer. */
public class TimerModel {

    /** Is this timer running? */
    private boolean isRunning;

    /** The start time of this timer in milliseconds. */
    private long startTime;

    /** Create a new timer. Initially it isn't running. */
    public TimerModel() {
        isRunning = false;
        startTime = 0;
    }

    /**
     * Start this timer. If invoked when a timer is already running,
     * this method will restart it.
     */
    public void start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    /** Stop this timer. */
    public void stop() {
        isRunning = false;
    }

    /**
     * Is this timer running?
     *
     * @return true if this timer is running; false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Return the elapsed time since this timer has started; return 0 if this
     * timer is not running. The elapsed time is given in milliseconds.
     *
     * @return elapsed time in milliseconds; 0 if this timer is not running.
     */
    public long elapsedTime() {
        return isRunning ? System.currentTimeMillis() - startTime : 0;
    }
}