package com.AshghalIntegration.Response.Utils;

import com.AshghalIntegration.Response.Constants.AIConstants;
import com.AshghalIntegration.Response.GenericResponse;
import com.AshghalIntegration.Response.SepmResponse;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class AIUtils {

    public GenericResponse nullAndEmptyCheck(String name, Object object) {
        if (null == object) {
            return new GenericResponse(
                    new SepmResponse(
                            "VALUE_NULL_001",
                            "Invalid/missing value : " + name,
                            AIConstants.STATUS_FAILURE));
        }
        if (object.getClass() == String.class) {
            if (object.toString().isEmpty()) {
                return new GenericResponse(
                        new SepmResponse(
                                "VALUE_EMPTY_001",
                                "Empty value : " + name,
                                AIConstants.STATUS_FAILURE));
            }
        }

        if (object.getClass() == ArrayList.class ||
                object.getClass() == HashSet.class ||
                object.getClass() == TreeSet.class) {
            if (((AbstractCollection<?>) object).size() <= 0) {
                return new GenericResponse(
                        new SepmResponse(
                                "COLLECTION_EMPTY_001",
                                "Empty collection : " + name,
                                AIConstants.STATUS_FAILURE));
            }
        }

        return new GenericResponse(new SepmResponse(
                "NULL_EMPTY_SUCCESS_001",
                "Valid param : " + name,
                AIConstants.STATUS_SUCCESS
        ));
    }

    public GenericResponse genericResponseNotFound(String param) {
        return new GenericResponse(new SepmResponse(
                "NOT_FOUND_001",
                param + " not found.",
                AIConstants.STATUS_FAILURE
        ));
    }

    public GenericResponse genericResponseSuccess(String responseMessage) {
        return new GenericResponse(
                new SepmResponse(
                        "SUCCESS_001",
                        responseMessage,
                        AIConstants.STATUS_SUCCESS
                )
        );
    }

    public SepmResponse sepmResponseSuccess(String responseMessage) {
        return
                new SepmResponse(
                        "SUCCESS_001",
                        responseMessage,
                        AIConstants.STATUS_SUCCESS

                );
    }

    public SepmResponse sepmResponseFailure(String responseMessage) {
        return
                new SepmResponse(
                        "FAILURE_001",
                        responseMessage,
                        AIConstants.STATUS_FAILURE

                );
    }

    public SepmResponse sepmResponseNotFound(String responseMessage) {
        return
                new SepmResponse(
                        "NOT_FOUND_001",
                        responseMessage,
                        AIConstants.STATUS_FAILURE

                );
    }
}
