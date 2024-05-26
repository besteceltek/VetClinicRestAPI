package com.beste.veterinary.core.result;

import com.beste.veterinary.core.Message;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(201, Message.CREATED, data);
    }

    public static <T> ResultData<T> updated(T data) {
        return new ResultData<>(200, Message.UPDATED, data);
    }

    public static Result deleted() {
        return new Result(200, Message.DELETED);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(200, Message.OK, data);
    }

    public static Result notFoundError() {
        return new Result(404, Message.NOT_FOUND);
    }

    public static Result notFoundDoctorError() {
        return new Result(404, Message.NOT_FOUND_DOCTOR);
    }

    public static Result notFoundAnimalError() {
        return new Result(404, Message.NOT_FOUND_ANIMAL);
    }

    public static Result notFoundCustomerError() {
        return new Result(404, Message.NOT_FOUND_CUSTOMER);
    }

    public static Result alreadyExistError() {
        return new Result(208, Message.ALREADY_EXIST);
    }

    public static Result badRequestError(String message) {
        return new Result(400, message);
    }

    public static Result vaccineDateNotApplicableError() {
        return new Result(400, Message.DATE_ERROR);
    }

    public static Result vaccineProtectionError() {
        return new Result(400, Message.VACCINE_ERROR);
    }

    public static Result doctorIdNullError() { return new Result(400, Message.DOCTOR_SELECT_ID_NULL); }

    public static Result animalIdNullError() { return new Result(400, Message.ANIMAL_SELECT_ID_NULL); }

    public static Result appointmentTimeError() {
        return new Result(400, Message.APP0_TIME_ERROR);
    }

    public static Result appointmentExistsError() {
        return new Result(400, Message.APP0_EXIST_ERROR);
    }

    public static Result doctorNotAvailableError() { return new Result(400, Message.DOCTOR_ERROR); }

    public static Result appointmentNotAvailableError() { return new Result(400, Message.APP0_NOT_AVA_ERROR); }
}
