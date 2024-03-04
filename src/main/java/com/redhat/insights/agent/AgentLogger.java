/* Copyright (C) Red Hat 2023-2024 */
package com.redhat.insights.agent;

import com.redhat.insights.logging.InsightsLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.simple.SimpleLogger;

public class AgentLogger implements InsightsLogger {

  private static AgentLogger instance;

  private Logger delegate;

  private AgentLogger(Class<?> clazz) {
    this.delegate = LoggerFactory.getLogger(clazz);
  }

  public static synchronized AgentLogger getLogger() {
    if (instance == null) {
      instance = new AgentLogger(AgentMain.class);
    }
    return instance;
  }

  public void setDebugDelegate() {
    System.setProperty(SimpleLogger.LOG_KEY_PREFIX + "com.redhat.insights.agent", "debug");
    this.delegate = LoggerFactory.getLogger("com.redhat.insights.agent.AgentLogger");
  }

  @Override
  public void debug(String message) {
    delegate.debug(message);
  }

  @Override
  public void debug(String message, Throwable err) {
    delegate.debug(message, err);
  }

  @Override
  public void error(String message) {
    delegate.error(message);
  }

  @Override
  public void error(String message, Throwable err) {
    delegate.error(message, err);
  }

  @Override
  public void info(String message) {
    delegate.info(message);
  }

  @Override
  public void warning(String message) {
    delegate.warn(message);
  }

  @Override
  public void warning(String message, Throwable err) {
    delegate.warn(message, err);
  }
}
