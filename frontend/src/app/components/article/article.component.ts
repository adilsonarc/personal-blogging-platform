import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-article',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './article.component.html',
  styleUrl: './article.component.css'
})
export class ArticleComponent {
  @Input() id: string | undefined;
  @Input() title: string | undefined;
  @Input() content: string | undefined;
  @Input() summary: string | undefined;
  @Input() publishedDate: string | undefined;
  @Input() lastUpdated: string | undefined;
  @Input() status: string | undefined;
  @Input() thumbnailUrl: string | undefined;
  @Input() slug: string | undefined;
  @Input() views: string | undefined;
  @Input() likes: string | undefined;
  @Input() source: string | undefined;
}
