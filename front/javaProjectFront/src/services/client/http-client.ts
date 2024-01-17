/* eslint-disable @typescript-eslint/no-explicit-any */
import axios from 'axios'

// import { SearchParamOptions } from '../../types/general'
// import { ACCESS_TOKEN_KEY } from '../../utils/global.constants'

const Axios = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  timeout: 5000000,
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
})

// Change request data/error here
// Axios.interceptors.request.use(config => {
//   // eslint-disable-next-line no-param-reassign
//   config.headers = {
//     ...config.headers,

//   }

//   return config
// })

// Change response data/error here
Axios.interceptors.response.use(
  response => response,
  error => {
    if (
      (error.response && error.response.status === 401) ||
      (error.response && error.response.status === 403) ||
      (error.response && error.response.data.message === 'PICKBAZAR_ERROR.NOT_AUTHORIZED')
    ) {
      // Cookies.remove(AUTH_TOKEN_KEY);
      // Router.reload();
    }

    return Promise.reject(error)
  },
)

export class HttpClient {
  static async get<T>(url: string, params?: any) {
    const response = await Axios.get<T>(url, params)

    return response.data
  }

  static async post<T>(url: string, data: unknown, options?: any) {
    const response = await Axios.post<T>(url, data, options)

    return response.data
  }

  static async put<T>(url: string, data: unknown, config?: any) {
    console.log(config)
    const response = await Axios.put<T>(url, data, config)

    return response.data
  }

  static async patch<T>(url: string, data: unknown) {
    const response = await Axios.patch<T>(url, data)

    return response.data
  }

  static async delete<T>(url: string) {
    const response = await Axios.delete<T>(url)

    return response.data
  }

  // static formatSearchParams(params: Partial<SearchParamOptions>) {
  //   return Object.entries(params)
  //     .filter(([, value]) => Boolean(value))
  //     .map(([k, v]) => (['type', 'categories', 'tags', 'author', 'manufacturer'].includes(k) ? `${k}.slug:${v}` : `${k}:${v}`))
  //     .join(';')
  // }
}
