package de.perschon.resultflow;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Util {

  private Util() {
    throw new UnsupportedOperationException("just use static methods");
  }


  static <T1, T, E> Result<T, E> zip(Supplier<Result<T1, E>> r1, Function<T1, T> transform) {
    return r1.get().map(transform);
  }

  static <T1, T2, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      BiFunction<T1, T2, T> transform) {
    return r1.get().andThen(
        x1 -> r2.get().map(
            x2 -> transform.apply(x1, x2)));
  }


  @FunctionalInterface
  interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
  }

  static <T1, T2, T3, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      Supplier<Result<T3, E>> r3,
      TriFunction<T1, T2, T3, T> transform) {

    return r1.get().andThen(
        x1 -> r2.get().andThen(
            x2 -> r3.get().map(
                x3 -> transform.apply(x1, x2, x3))));
  }


  @FunctionalInterface
  interface QuadFunction<A, B, C, D, R> {
    R apply(A a, B b, C c, D d);
  }

  static <T1, T2, T3, T4, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      Supplier<Result<T3, E>> r3,
      Supplier<Result<T4, E>> r4,
      QuadFunction<T1, T2, T3, T4, T> transform) {

    return r1.get().andThen(
        x1 -> r2.get().andThen(
            x2 -> r3.get().andThen(
                x3 -> r4.get().map(
                    x4 -> transform.apply(x1, x2, x3, x4)))));
  }



  @FunctionalInterface
  interface QuinFunction<A, B, C, D, E, R> {
    R apply(A a, B b, C c, D d, E e);
  }

  static <T1, T2, T3, T4, T5, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      Supplier<Result<T3, E>> r3,
      Supplier<Result<T4, E>> r4,
      Supplier<Result<T5, E>> r5,
      QuinFunction<T1, T2, T3, T4, T5, T> transform) {

    return r1.get().andThen(
        x1 -> r2.get().andThen(
            x2 -> r3.get().andThen(
                x3 -> r4.get().andThen(
                    x4 -> r5.get().map(
                        x5 -> transform.apply(x1, x2, x3, x4, x5))))));
  }



  @FunctionalInterface
  interface SextFunction<A, B, C, D, E, F, R> {
    R apply(A a, B b, C c, D d, E e, F f);
  }

  static <T1, T2, T3, T4, T5, T6, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      Supplier<Result<T3, E>> r3,
      Supplier<Result<T4, E>> r4,
      Supplier<Result<T5, E>> r5,
      Supplier<Result<T6, E>> r6,
      SextFunction<T1, T2, T3, T4, T5, T6, T> transform) {

    return r1.get().andThen(
        x1 -> r2.get().andThen(
            x2 -> r3.get().andThen(
                x3 -> r4.get().andThen(
                    x4 -> r5.get().andThen(
                        x5 -> r6.get().map(
                            x6 -> transform.apply(x1, x2, x3, x4, x5, x6)))))));
  }

  @FunctionalInterface
  interface SeptFunction<A, B, C, D, E, F, G, R> {
    R apply(A a, B b, C c, D d, E e, F f, G g);
  }

  static <T1, T2, T3, T4, T5, T6, T7, T, E> Result<T, E> zip(
      Supplier<Result<T1, E>> r1,
      Supplier<Result<T2, E>> r2,
      Supplier<Result<T3, E>> r3,
      Supplier<Result<T4, E>> r4,
      Supplier<Result<T5, E>> r5,
      Supplier<Result<T6, E>> r6,
      Supplier<Result<T7, E>> r7,
      SeptFunction<T1, T2, T3, T4, T5, T6, T7, T> transform) {

    return r1.get().andThen(
        x1 -> r2.get().andThen(
            x2 -> r3.get().andThen(
                x3 -> r4.get().andThen(
                    x4 -> r5.get().andThen(
                        x5 -> r6.get().andThen(
                            x6 -> r7.get().map(
                                x7 -> transform.apply(x1, x2, x3, x4, x5, x6, x7))))))));
  }


}
