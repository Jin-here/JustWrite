### 分支

##### 分类：

- master：生产环境代码，只能从其他分支合并，不能再这个分支直接修改
- develop：开发主分支，包含所有要发布到下一个release的代码
- feature：功能分支，一旦开发完成，将合并到develop分支
- release：发布分支，一旦开发完成，将合并到develop和master分支
- hotfix：当我们在master分支发现新的bug时候，我们需要创建一个hotfix分支。完成后，将合并到master和develop分支

各分支关系如下图：
![分支关系图](分支关系图.png)

##### 命名
前缀/描述，例如feature/login或hotfix/register

### 注意点
- 不要用快捷键自动对齐代码，这样会造成git提交时出现多处没用的修改，不方便日后查看历史提交，所以写代码时就应书写规范
- 测试代码提交前应恢复原状
- 当进行重构时，应等重构完，其他开发者才能后续的功能开发，否则融合时会引起大量冲突，且难以解决

### 常用命令
- stash
- reset
- revert
- merge
### 工具
1. [git flow](https://www.git-tower.com/learn/git/ebook/cn/command-line/advanced-topics/git-flow)