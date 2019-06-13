package com.bookstore.util;


import java.awt.Button;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
 
/** 
* Description: 
* �һ��Socket��������Ӧ���û����� 
* @author Lee 
* */
public class ServletSocket { 
	private String ll = null;
	private String judge = null;
ArrayList MSG = new ArrayList<>(); 
ArrayList RES = new ArrayList<>();
/**
 * Description:
 * ��ʼ������
 * */
public void init(){
  MSG.add("hellow");
  RES.add("hi");
  judge= null;
}
/**
 * Description:
 * �һ��Socket��������Ӧ����û�����
 * */
public void con(){
  init();
  ServerSocket server = null;
  try{
    //��ָ���˿ڴһ��Socket�����
    server = new ServerSocket(9011);  
 
    //�ȴ��ͻ���Socketʵ����������һ���߳�ȥ��Ӧ�ÿͻ���ʵ��
    new Response(server.accept()).start();
  }catch(IOException e){
    e.printStackTrace();
  }finally{
    try{
      server.close();
    }catch(IOException e){
      e.printStackTrace();
    }
 
  }
}
 
/**
 * Description:
 * �����û���������ݣ�������Ӧ������
 * 
 * @param msg �ͻ������������
 * @return ���ط���˻ظ�������
 * */
public String getMsg(){
 
  /*for(int i=1;i<MSG.size();i++){
    if(msg.contains(MSG.get(i))){
      res = RES.get(i);
    }
  }*/
 
  return this.ll;
}public String getJudge() {
	return this.judge;
}

public void setMsg(String msg) {
	this.ll = msg;
}
 
 
/*public static void main(String[] args) {
  // TODO Auto-generated method stub
  new ServletSocketDemo().test1();
}*/
 
/**
 * Description:
 * ��Ӧ�û�
 * @author Lee
 * */
class Response extends Thread implements Runnable{
  Socket client;
  /**
   * Description:
   * Ĭ�Ϲ����� 
   * */
  public Response(){}
  /**
   * Description:
   * ��ʼ��Socket
   * */
  public Response(Socket client){
    this.client = client;
  }
  BookGet bookGet;
 
  @Override
  public void run(){
    Scanner input = null;
    PrintWriter output = null;
 
    try{
      //��ȡ�û��˵�����������
      input = new Scanner(client.getInputStream());
      output = new PrintWriter(client.getOutputStream());
 
      //output.println("��ӭ����!");
      output.println(getMsg());
      output.flush();
 
      //�ȴ��ͻ��˵�����
      String content = null;
      
      while(input.hasNext()){
        content = input.nextLine();
        //�����û��˵����룬������Ӧ�ķ�Ӧ
        if(content.equalsIgnoreCase("#")) {
          break;
        }else {
          /*output.println(ll);
          output.flush();*/
        }
      }
 
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      //�ر���Դ
      input.close();
      output.close();
    }
  }
}

}