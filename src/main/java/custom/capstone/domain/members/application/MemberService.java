package custom.capstone.domain.members.application;

import custom.capstone.domain.members.dao.MemberRepository;
import custom.capstone.domain.members.domain.Member;
import custom.capstone.domain.members.dto.MemberResponseDto;
import custom.capstone.domain.members.dto.MemberSaveRequestDto;
import custom.capstone.domain.members.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(MemberSaveRequestDto requestDto) {
        Member member = Member.builder()
                .nickname(requestDto.nickname())
                .password(requestDto.password())
                .email(requestDto.email())
                .phoneNum(requestDto.phoneNum())
                .occupation(requestDto.occupation())
                .build();

        return memberRepository.save(member).getId();
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public Long updateMember(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        member.update(requestDto.nickname(), requestDto.password(), requestDto.email(), requestDto.phoneNum());

        return id;
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(memberRepository::delete);
    }

    /**
     * 회원 조회
     */
    @Transactional
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    @Transactional
    public Optional<Member> findMemberByNickname(String nickname) {
        return memberRepository.findMemberByNickname(nickname);
    }

    @Transactional
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public MemberResponseDto findMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        return new MemberResponseDto(member.getId(), member.getNickname(), member.getEmail(), member.getPhoneNum(), member.getOccupation(), member.getStatus());
    }
}