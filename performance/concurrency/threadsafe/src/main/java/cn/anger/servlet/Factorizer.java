package cn.anger.servlet;

import cn.anger.util.FactorizationUtil;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author : anger
 */
public class Factorizer extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) {
        final BigInteger i = extractFromRequest(req);
        final BigInteger[] factors = FactorizationUtil.doFactorization(i);
        encodeIntoResponse(res, factors);
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return (BigInteger) request.getAttribute("number");
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
        try (PrintWriter writer = response.getWriter();){
            writer.write(Arrays.toString(factors));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
