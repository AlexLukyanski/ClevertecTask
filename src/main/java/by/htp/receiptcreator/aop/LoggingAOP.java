package by.htp.receiptcreator.aop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAOP {

    private final static Logger log = LogManager.getRootLogger();

    @AfterThrowing(pointcut = "execution(* by.htp.receiptcreator.controller.*.*(..))", throwing = "e")
    public void logging(Throwable e) {

        log.log(Level.ERROR, "Something's wrong creating receipt", e);
    }
}
