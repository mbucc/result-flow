package de.perschon.resultflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import lombok.Data;

import java.util.function.Supplier;
import org.junit.Test;

public class ZipTest {

  @Data
  static class TestZip1 {
    final String x;
  }

  @Data
  static class TestZip2 {
    final String x1;
    final String x2;
  }

  @Data
  static class TestZip3 {
    final String x1;
    final String x2;
    final String x3;
  }

  private Supplier<Result<String, String>> lazyOk(String x) {
    return () -> Result.ok(x);
  }

  private Supplier<Result<String, String>> lazyErr(String x) {
    return () -> Result.err(x);
  }

  @Test
  public void testZip1Success() {
    Result<TestZip1, String> y0 = Util.zip(lazyOk("abc"), TestZip1::new);
    assertTrue(y0.isOk());

    Optional<TestZip1> y1 = y0.getValue();
    assertTrue(y1.isPresent());

    assertEquals("abc", y1.get().x);
  }


  @Test
  public void testZip1Failure() {
    Result<TestZip1, String> y0 = Util.zip(lazyErr("abc"), TestZip1::new);
    assertTrue(y0.isErr());

    Optional<String> y1 = y0.getError();
    assertTrue(y1.isPresent());

    assertEquals("abc", y1.get());
  }


  @Test
  public void testZip2Success() {
    Result<TestZip2, String> y0 = Util.zip(lazyOk("abc"), lazyOk("def"), TestZip2::new);
    assertTrue(y0.isOk());

    Optional<TestZip2> y1 = y0.getValue();
    assertTrue(y1.isPresent());

    assertEquals("abc", y1.get().x1);
  }


  @Test
  public void testZip2Failure() {
    Result<TestZip2, String> y0 = Util.zip(lazyErr("abc"), lazyOk("def"), TestZip2::new);
    assertTrue(y0.isErr());

    Result<TestZip2, String> y1 = Util.zip(lazyOk("abc"), lazyErr("def"), TestZip2::new);
    assertTrue(y1.isErr());

    Result<TestZip2, String> y2 = Util.zip(lazyErr("abc"), lazyErr("def"), TestZip2::new);
    assertTrue(y2.isErr());
  }


  @Test
  public void testZip3Success() {
    Result<TestZip3, String> y0 = Util.zip(lazyOk("a"), lazyOk("b"), lazyOk("c"), TestZip3::new);
    assertTrue(y0.isOk());

    Optional<TestZip3> y1 = y0.getValue();
    assertTrue(y1.isPresent());

    TestZip3 y2 = y1.get();

    assertEquals("a", y2.x1);
    assertEquals("b", y2.x2);
    assertEquals("c", y2.x3);
  }


}
