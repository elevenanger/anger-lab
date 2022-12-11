package cn.anger.servlet;

import cn.anger.annotation.ThreadSafe;

/**
 * @author : anger
 * 因式分解
 * 这个 Servlet 是无状态的，
 * 它自身没有实例字段和引用，
 * 也没有来自其它类的字段，
 * 特定计算的瞬时状态仅存于局部变量上，
 * 它存储于线程的栈上，
 * 而且只能被当前的处理线程所访问。
 * 一个线程访问 StatelessFactorizer
 * 不会对另外的访问相同 StatelessFactorizer 对象的线程的结果产生影响，
 * 因为两个线程之间没有共享的状态，
 * 就好像它们在访问不同的 StatelessFactorizer 实例一样。
 * 因为一个线程对于无状态对象的访问和操作不会影响其它线程访问此对象以及使用其执行操作的准确性，
 * 所以无状态对象是线程安全的。
 */
@ThreadSafe
public class StatelessFactorizer extends Factorizer { }
