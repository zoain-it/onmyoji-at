package com.ikrka;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
  /**
   * Rigorous Test :-)
   * 
   * @throws ExecutionException
   * @throws InterruptedException
   */
  @Test
  public void test() {

    ReferenceCountingGC a = new ReferenceCountingGC("objA");
    ReferenceCountingGC b = new ReferenceCountingGC("objB");

    a.instance = b;
    b.instance = a;

    a = null;
    b = null;

  }

  public class ReferenceCountingGC {

    public Object instance;

    public ReferenceCountingGC(String name) {
    }

  }

}
