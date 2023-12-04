import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ArticleResponse} from "./article.response";
import {ArticleRequest} from "./article.request";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) {

  }

  getAllArticles(): Observable<ArticleResponse[]> {
    return this.http.get<ArticleResponse[]>("articles")
  }

  getArticleById(id: string): Observable<ArticleResponse[]> {
    return this.http.get<ArticleResponse[]>(`articles/${id}`);
  }

  createArticle(article: ArticleRequest): Observable<ArticleResponse> {
    return this.http.post<ArticleResponse>(`articles`, article);
  }

  updateArticle(id: string, article: ArticleRequest): Observable<ArticleResponse> {
    return this.http.post<ArticleResponse>(`articles/${id}`, article);
  }

  deleteArticle(id: string, article: ArticleRequest): Observable<void> {
    return this.http.delete<void>(`articles/${id}`);
  }
}
