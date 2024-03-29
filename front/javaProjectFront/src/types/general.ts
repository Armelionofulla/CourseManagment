export interface SearchParamOptions {}

export interface QueryOptions {
  language: string
  page?: number
  limit?: number
}

export interface PaginatorInfo<T> {
  current_page: number
  data: T[]
  first_page_url: string
  from: number
  last_page: number
  last_page_url: string
  links: string[]
  next_page_url: string | null
  path: string
  per_page: number
  prev_page_url: string | null
  to: number
  total: number
}

export interface Error {
  response: {
    data: {
      message: string
    }
  }
}

export interface Props {
  children?: React.ReactNode
}

export interface Response {
  message: string
  code: string
}

export interface Comments {
  text: string
}
