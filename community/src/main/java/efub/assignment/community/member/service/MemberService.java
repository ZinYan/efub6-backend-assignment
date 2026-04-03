package efub.assignment.community.member.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.request.CreateMemberRequestDto;
import efub.assignment.community.member.dto.response.CreateMemberResponseDto;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public CreateMemberResponseDto createMember(CreateMemberRequestDto requestDto){
        if(memberRepository.existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하 email입니다."+requestDto.getEmail());
        }
        Member member = requestDto.toEntity();
        Member savedMember = memberRepository.save(member);
        return CreateMemberResponseDto.from(savedMember);
    }
}
