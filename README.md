



另外还有一点需要注意，ResponseBodyAdvice 在你使用了 @ResponseBody 注解的时候才会生效，RequestBodyAdvice 在你使用了 @RequestBody 注解的时候才会生效，换言之，前后端都是 JSON 交互的时候，这两个才有用。

发布到网上



