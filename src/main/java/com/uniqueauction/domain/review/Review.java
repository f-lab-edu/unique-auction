@ToString
@Getter
@Entity
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	private int score;

	private String content;

	@Builder
	public Review(User user, Product product, int score, String content) {
		this.user = user;
		this.product = product;
		this.score = score;
		this.content = content;
	}

	//==생성 메서드==//
	public static Review createReview(User user, Product product, SaveReviewRequest reviewRequest) {
		return Review.builder()
			.user(user)
			.product(product)
			.score(reviewRequest.getScore())
			.content(reviewRequest.getContent())
			.build();
	}
}
