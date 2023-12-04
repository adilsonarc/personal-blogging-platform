import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ArticleResponse} from "../../services/article/article.response";
import {Observable} from "rxjs";
import {ArticleService} from "../../services/article/article.service";

@Component({
  selector: 'app-blog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.css'
})
export class BlogComponent {

  articles$: Observable<ArticleResponse[]>;

  constructor(private articleService: ArticleService) {
    this.articles$ = articleService.getAllArticles();
  }

}
