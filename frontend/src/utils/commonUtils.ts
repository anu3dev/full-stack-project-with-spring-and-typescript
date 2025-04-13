export const getSessionStorageValue = (key: string): string | null => {
  try {
    return sessionStorage.getItem(key)
  } catch (error) {
    console.error(`Error reading session storage key "${key}":`, error)
    return null
  }
}
