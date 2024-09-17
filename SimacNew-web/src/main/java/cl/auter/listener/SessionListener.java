/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
      
        arg0.getSession().invalidate();
        
  }
}