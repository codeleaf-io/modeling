package io.codeleaf.modeling.task;

public interface Task<O> {

    Class<O> getOutputType();

}
