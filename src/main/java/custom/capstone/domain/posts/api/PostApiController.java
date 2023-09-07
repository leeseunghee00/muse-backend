package custom.capstone.domain.posts.api;

import custom.capstone.domain.posts.application.PostService;
import custom.capstone.domain.posts.domain.Post;
import custom.capstone.domain.posts.dto.request.PostSaveRequestDto;
import custom.capstone.domain.posts.dto.request.PostUpdateRequestDto;
import custom.capstone.domain.posts.dto.response.PostListResponseDto;
import custom.capstone.domain.posts.dto.response.PostResponseDto;
import custom.capstone.domain.posts.dto.response.PostSaveResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @Operation(summary = "게시글 등록")
    @PostMapping
    public PostSaveResponseDto savePost(@RequestBody final PostSaveRequestDto requestDto) {
        return postService.savePost(requestDto);
    };

    @Operation(summary = "게시글 수정")
    @PatchMapping("/{postId}")
    public Long updatePost(@PathVariable("postId") final Long id,
                           @RequestBody final PostUpdateRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @Operation(summary = "게시글 페이징 조회")
    @GetMapping
    public Page<PostListResponseDto> findAll(final Pageable pageable) {
        return postService.findAll(pageable)
                .map(PostListResponseDto::new);
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{postId}")
    public PostResponseDto findDetailById(@PathVariable("postId") final Long id) {
        return postService.findDetailById(id);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable("postId") final Long id) {
        postService.deletePost(id);
        return id;
    }

    @Operation(summary = "키워드 검색")
    @GetMapping("/search")
    public Page<PostListResponseDto> searchPosts(
            @RequestParam(value = "keyword") final String keyword,
            final Model model,
            @PageableDefault(direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        final Page<PostListResponseDto> searchPosts = postService.searchPosts(keyword, pageable).map(PostListResponseDto::new);

        model.addAttribute("searchPosts", searchPosts);

        return searchPosts;
    }
}
