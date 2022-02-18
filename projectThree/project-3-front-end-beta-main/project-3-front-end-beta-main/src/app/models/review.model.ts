export class Review {
  reviewId: number = 0;
  userId: number = 0;
  productId: number = 0;
  date: Date = new Date();
  title: string = "";
  rating: number = 0;
  review: string = "";
}

export class UserReview {
  reviewId: number = 0;
  userId: number = 0;
  firstName: string = "";
  lastName: string = "";
  productId: number = 0;
  date: Date = new Date();
  title: string = "";
  rating: number = 0;
  review: string = "";
}
