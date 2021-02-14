package recursion.schemas


/*
The origin of that peculiar name is surprisingly straightforward, for once:

-the fixed-point of function f is x such that f(x) = x.
-fix is the function that, given a function, returns its fixed-point.
-if fix(f) is the fixed-point of f, then fix(f) = f(fix(f))
-that is exactly the definition we just wrote as Scala code.
 */
case class Fix[F[_]](value: F[Fix[F]])