package dev.bitbakery.boilerplate.post.ui

import dev.bitbakery.boilerplate.arch.MolecularViewModel

class PostListViewModel(
    presenter: PostListPresenter,
) : MolecularViewModel<PostListEvent, PostListState>(presenter)
