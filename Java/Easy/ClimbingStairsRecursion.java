class ClimbingStairsRecursion {
  public int climbStairs(int n) {
    return rec(n + 1);
  }

  private int rec(int n) {
    if(n == 0 || n == 1) return n;
    return rec(n - 1) + rec(n - 2);
  }
}
