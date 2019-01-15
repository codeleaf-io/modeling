package io.codeleaf.modeling.service;

import io.codeleaf.modeling.data.ValueType;

import java.util.Map;

public interface ServiceCaller {

    Service getService();

    ValueType call(Procedure procedure, Map<String, ValueType> arguments) throws ProcedureExecutionException;

}
