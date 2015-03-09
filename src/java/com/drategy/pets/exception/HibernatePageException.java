package com.drategy.pets.exception;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: langtong</p>
 * @author not attributable
 * @version 1.0
 */

public class HibernatePageException
    extends BaseException {
  public HibernatePageException() {
    super("Hibernate��ҳ�쳣");
  }

  public HibernatePageException(String message) {
    super("Hibernate��ҳ�쳣��" + message);
  }

}
