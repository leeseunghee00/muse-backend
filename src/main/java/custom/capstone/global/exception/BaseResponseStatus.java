package custom.capstone.global.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum BaseResponseStatus {
    /**
     * 1000: 요청 성공
     */
    // MEMBER
    JOIN_SUCCESS(HttpStatus.CREATED, 1000, "회원가입에 성공했습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, 1001, "성공적으로 로그인 되었습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, 1002, "성공적으로 회원 정보가 수정되었습니다."),

    /**
     * 2000: Request 오류
     */
    // MEMBER
    EMPTY_JWT(HttpStatus.UNAUTHORIZED, 2000, "JWT 가 존재하지 않습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, 2001, "유효하지 않은 JWT 입니다."),
    MEMBER_EXISTS_EMAIL(HttpStatus.CONFLICT, 2002, "이미 존재하는 이메일입니다."),
    MEMBER_EXISTS_NICKNAME(HttpStatus.CONFLICT, 2003, "이미 존재하는 닉네임입니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, 2004, "잘못된 비밀번호입니다."),
    WRONG_JOIN_PASSWORD(HttpStatus.BAD_REQUEST, 2005, "비밀번호 확인이 일치하지 않습니다."),
    FOLLOW_EXISTS_TO(HttpStatus.CONFLICT, 2006, "이미 팔로우 했습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 2007, "해당 사용자를 찾을 수 없습니다."),
    INVALID_ACCESS(HttpStatus.UNAUTHORIZED, 2008, "접근 권한이 없습니다."),

    // POST
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, 2100, "해당 게시글을 찾을 수 없습니다."),

    // MAGAZINE
    MAGAZINE_NOT_FOUND(HttpStatus.NOT_FOUND, 2200, "해당 매거진을 찾을 수 없습니다."),

    // NOTICE
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, 2300, "해당 공지사항을 찾을 수 없습니다."),

    // INQUIRY & ANSWER
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, 2400, "해당 1:1 문의를 찾을 수 없습니다."),
    INQUIRY_CANNOT_DELETE(HttpStatus.UNAUTHORIZED, 2401, "문의 작성자만 삭제할 수 있습니다."),
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, 2402, "해당 1:1 문의에 대한 답변을 찾을 수 없습니다."),

    // INTEREST
    INTEREST_NOT_FOUND(HttpStatus.NOT_FOUND, 2500, "해당 좋아요 게시글을 찾을 수 없습니다."),
    INTEREST_EXISTS_STATUS(HttpStatus.CONFLICT, 2501, "이미 좋아요 한 상태입니다."),

    // REVIEW
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, 2600, "해당 후기를 찾을 수 없습니다."),

    // TRADING
    TRADING_NOT_FOUND(HttpStatus.NOT_FOUND, 2700, "해당 거래를 찾을 수 없습니다."),

    // DTO
    DTO_INVALID(HttpStatus.BAD_REQUEST, 2800, "DTO 입력이 바르지 않습니다."),

    /**
     * 3000: Response 오류
     */
    // COMMON
    RESPONSE_ERROR(HttpStatus.NOT_FOUND, 3000, "값을 불러오는데 실패하였습니다."),
    PASSWORD_ENCRYPTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 3001, "비밀번호 암호화에 실패하였습니다."),

    /**
     * 4000: DB, Server 오류
     */
    DATABASE_ERROR(HttpStatus.SERVICE_UNAVAILABLE, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(HttpStatus.BAD_GATEWAY, 4001, "서버 연결에 실패하였습니다."),

    // MEMBER
    MEMBER_FAILED_JOIN(HttpStatus.INTERNAL_SERVER_ERROR, 2009, "회원가입에 실패하였습니다."),
    MEMBER_FAILED_LOGIN(HttpStatus.BAD_REQUEST, 2010, "로그인에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    private BaseResponseStatus(final HttpStatus httpStatus, final int code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
