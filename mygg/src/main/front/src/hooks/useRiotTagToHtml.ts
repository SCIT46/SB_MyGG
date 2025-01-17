// 사용중인 컴포넌트
// 아이템 이미지 컴포넌트
export const useRiotTagToHtml = (riotTag: string) => {
  const html = riotTag
    .replace(/<attention>(.*?)<\/attention>/g, `<strong>$1</strong>`)
    .replace(/<physicalDamage>(.*?)<\/physicalDamage>/g, `<strong>$1</strong>`)
    .replace(/<status>(.*?)<\/status>/g, `<strong>$1</strong>`)
    .replace(/<OnHit>(.*?)<\/OnHit>/g, `<strong>$1</strong>`)
    .replace(/<br>(.*?)<\/br>/g, `<br>$1</br>`);
  return html;
};
