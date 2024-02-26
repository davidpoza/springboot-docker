```mermaid
erDiagram
  FEED ||--o{ ARTICLE : publishes
  CURED_ARTICLE ||--|{ ARTICLE : referes
  CURED_ARTICLE }|--|{ TAG : tagged


  FEED {
    string title
    string url
  }

  ARTICLE {
    string title
    string url
    date published_at
    string fullContent
    string summaryContent
  }

  CURED_ARTICLE {
    string title
    string content
    date published_at
  }

  TAG {
    string name
  }
```